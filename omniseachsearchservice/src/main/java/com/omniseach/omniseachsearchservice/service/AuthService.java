package com.omniseach.omniseachsearchservice.service;

import com.omniseach.omniseachsearchservice.dto.LoginRequestDto;
import com.omniseach.omniseachsearchservice.dto.RegisterRequestDto;
import com.omniseach.omniseachsearchservice.model.RefreshToken;

public interface AuthService {

    void register(RegisterRequestDto dto);

    String login(LoginRequestDto dto);

    RefreshToken createRefreshToken(String userId);

    String refreshAccessToken(String refreshToken);

    void verifyEmail(String token);

    void forgotPassword(String email);

    void resetPassword(String token, String newPassword);
}
