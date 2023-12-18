import {AxiosInstance} from 'axios';
import {LocalStorageKeys} from '@/components/auth/model/localStorageKeys';

export const useSetAuthorizationInterceptor = (apiInstance: AxiosInstance) => {
  apiInstance.interceptors.request.use(function (config) {
    const tokens = localStorage.getItem(LocalStorageKeys.TOKENS_KEY);
    const accessToken = tokens? JSON.parse(tokens).token_access : null;
    config.headers.Authorization =  accessToken ? `Bearer ${accessToken}` : '';
    return config;
  });
};