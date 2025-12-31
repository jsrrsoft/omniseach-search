import axios from "axios";
import { getAccessToken, getRefreshToken, setTokens, clearTokens } from "../utils/tokenStorage";

const api = axios.create({
  baseURL: "http://localhost:8080",
});

api.interceptors.request.use((config) => {
  const token = getAccessToken();
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

api.interceptors.response.use(
  (res) => res,
  async (error) => {
    if (error.response?.status === 401) {
      try {
        const refreshToken = getRefreshToken();
        const res = await axios.post(
          "http://localhost:8080/api/auth/refresh",
          null,
          { params: { refreshToken } }
        );

        setTokens(res.data.accessToken, refreshToken);
        error.config.headers.Authorization = `Bearer ${res.data.accessToken}`;
        return axios(error.config);
      } catch {
        clearTokens();
        window.location.href = "/";
      }
    }
    return Promise.reject(error);
  }
);

export default api;
