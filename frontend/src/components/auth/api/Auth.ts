import { profile } from '@/components/auth/model/Profile';
import LoginData from '@/components/auth/model/loginData';
import { LocalStorageKeys} from '@/components/auth/model/localStorageKeys';
import axiosApiInstance from '@/components/auth/api/AxiosApiInstance';
import router from '@/router/index';


class Auth {
  private tokens = {
    token_access: '',
  };

  profile = profile;

  isAuthorized() {
    return !!localStorage.getItem(LocalStorageKeys.TOKENS_KEY);
  }

  getTokens() {
    const tokensStr = localStorage.getItem(LocalStorageKeys.TOKENS_KEY);
    if (tokensStr) {
      return JSON.parse(tokensStr);
    }
    return '';
  }

  updateTokens(newAccessToken: string) {
    this.tokens.token_access = newAccessToken;
    localStorage.setItem(LocalStorageKeys.TOKENS_KEY, JSON.stringify(this.tokens));
  }

  tokensClean() {
    localStorage.removeItem(LocalStorageKeys.TOKENS_KEY);
    this.tokens.token_access = '';
  }

  // @ts-ignore
  async sendLoginData(loginInputData: LoginData) {
    if (loginInputData.isContainsValues()) {
      this.profile.setAuthData(loginInputData);

      // по дефолту с сервера приходит 200,
      // но внутри обертки в теле ответа - поле status
      try {
        //const response = await axiosApiInstance.post('https://run.mocky.io/v3/030c10c8-fba7-4ae5-baf8-f98e26d37842', {
        const response = await axiosApiInstance.post('/auth/login', {
          ...loginInputData,
        });
        if (response.status !== 200) {
          return Promise.reject(response.status);
        }
        if (response.data.email) {
          this.profile.setUserInfo(response.data.role, response.data.fullName);
          localStorage.setItem(LocalStorageKeys.FIO_KEY, response.data.fullName);
          localStorage.setItem(LocalStorageKeys.ROLE_KEY, response.data.role);
          localStorage.setItem(LocalStorageKeys.USERNAME_KEY, response.data.email);

          this.updateTokens(
            response.data.token_access,
          );

          return Promise.resolve(response.status);
        }
        return Promise.reject(new Error('Токен не найден в ответе от сервера'));
        // в случае, если ответ с сервера не пришел или 500 ошибка
      } catch (error: any) {
        return Promise.reject(error.response?.status);
      }
    } else {
      return Promise.reject(
        new Error('Форма авторизации должна содержать данные'),
      );
    }
  }

  async logout() {
    await router.replace({ name: 'auth' });
    this.profile.clean();
    this.tokensClean();
  }

  async logoutWithoutBackendTokensClean() {
    await router.replace({ name: 'login' });
    this.profile.clean();
    this.tokensClean();
  }
}

const auth = new Auth();
export default auth;
