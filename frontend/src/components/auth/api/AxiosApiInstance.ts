import axios from 'axios';
import { useSetAuthorizationInterceptor } from '@/components/auth/api/SetAuthorizationInterceptor';

const axiosApiInstance = axios.create({
  baseURL: 'http://localhost:8080/schedule/',
  headers: {
    'Content-Type': 'application/json; charset=UTF-8',
  },
});

useSetAuthorizationInterceptor(axiosApiInstance);

export default axiosApiInstance;
