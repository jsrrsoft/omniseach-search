import api from "../api/axiosInstance";
import { setTokens } from "../utils/tokenStorage";

export const login = async (data) => {
  const res = await api.post("/api/auth/login", data);
  setTokens(res.data.accessToken, res.data.refreshToken);
};

export const register = (data) =>
  api.post("/api/auth/register", data);

export const forgotPassword = (email) =>
  api.post("/api/auth/forgot-password", null, { params: { email } });

export const resetPassword = (token, password) =>
  api.post("/api/auth/reset-password", null, {
    params: { token, password },
  });
