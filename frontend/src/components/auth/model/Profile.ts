import LoginData from '@/components/auth/model/loginData';

class Profile {
  loginData = new LoginData();

  fullName = '';

  role = '';

  getAuthData() {
    return this.loginData;
  }

  setAuthData(authData: LoginData) {
    this.loginData = authData;
  }

  setUserInfo(role: string, fullName: string) {
    this.role = role;
    this.fullName = fullName;
  }

  getUserRole() {
    return this.role;
  }

  clean() {
    this.loginData = new LoginData();
    this.role = '';
    this.fullName = '';
  }
}

export const profile = new Profile();
