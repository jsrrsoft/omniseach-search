package com.omniseach.omniseachsearchservice.service.impl;

import java.time.Instant;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.omniseach.omniseachsearchservice.dto.LoginRequestDto;
import com.omniseach.omniseachsearchservice.dto.RegisterRequestDto;
import com.omniseach.omniseachsearchservice.exception.InvalidSearchException;
import com.omniseach.omniseachsearchservice.model.RefreshToken;
import com.omniseach.omniseachsearchservice.model.User;
import com.omniseach.omniseachsearchservice.repository.RefreshTokenRepository;
import com.omniseach.omniseachsearchservice.repository.UserRepository;
import com.omniseach.omniseachsearchservice.service.AuthService;
import com.omniseach.omniseachsearchservice.service.CaptchaService;
import com.omniseach.omniseachsearchservice.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final CaptchaService captchaService;
    private final JwtUtil jwtUtil;

    @Override
    public void register(RegisterRequestDto dto) {

        if (!captchaService.verify(dto.getCaptcha())) {
            throw new InvalidSearchException("Invalid captcha");
        }

        userRepository.findByUsername(dto.getUsername())
                .ifPresent(u -> { throw new InvalidSearchException("Username already exists"); });

        userRepository.findByEmail(dto.getEmail())
                .ifPresent(u -> { throw new InvalidSearchException("Email already registered"); });

        User user = new User();
        user.setFullName(dto.getFullName());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        user.setRole("USER");
        user.setEmailVerified(false);
        user.setVerificationToken(UUID.randomUUID().toString());
        user.setFailedLoginAttempts(0);
        user.setAccountLocked(false);

        userRepository.save(user);
        // email sending can be added later
    }

    @Override
    public String login(LoginRequestDto dto) {

        if (!captchaService.verify(dto.getCaptcha())) {
            throw new InvalidSearchException("Invalid captcha");
        }

        User user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new InvalidSearchException("Invalid credentials"));

        if (user.isAccountLocked()) {
            throw new InvalidSearchException("Account locked");
        }

        if (!user.isEmailVerified()) {
            throw new InvalidSearchException("Verify email before login");
        }

        if (!passwordEncoder.matches(dto.getPassword(), user.getPasswordHash())) {

            user.setFailedLoginAttempts(user.getFailedLoginAttempts() + 1);
            if (user.getFailedLoginAttempts() >= 5) {
                user.setAccountLocked(true);
            }
            userRepository.save(user);
            throw new InvalidSearchException("Invalid credentials");
        }

        user.setFailedLoginAttempts(0);
        userRepository.save(user);

        return jwtUtil.generateToken(user.getUsername(), user.getRole());
    }

    @Override
    public RefreshToken createRefreshToken(String userId) {

        RefreshToken token = new RefreshToken();
        token.setUserId(userId);
        token.setToken(UUID.randomUUID().toString());
        token.setExpiry(Instant.now().plusSeconds(7 * 86400));

        return refreshTokenRepository.save(token);
    }

    @Override
    public String refreshAccessToken(String refreshToken) {

        RefreshToken token = refreshTokenRepository.findByToken(refreshToken)
                .orElseThrow(() -> new InvalidSearchException("Invalid refresh token"));

        if (token.getExpiry().isBefore(Instant.now())) {
            throw new InvalidSearchException("Refresh token expired");
        }

        return jwtUtil.generateToken(token.getUserId(), "USER");
    }

    @Override
    public void verifyEmail(String token) {

        User user = userRepository.findByVerificationToken(token)
                .orElseThrow(() -> new InvalidSearchException("Invalid verification token"));

        user.setEmailVerified(true);
        user.setVerificationToken(null);
        userRepository.save(user);
    }

    @Override
    public void forgotPassword(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new InvalidSearchException("Email not found"));

        user.setVerificationToken(UUID.randomUUID().toString());
        userRepository.save(user);
    }

    @Override
    public void resetPassword(String token, String newPassword) {

        User user = userRepository.findByVerificationToken(token)
                .orElseThrow(() -> new InvalidSearchException("Invalid token"));

        user.setPasswordHash(passwordEncoder.encode(newPassword));
        user.setVerificationToken(null);
        user.setFailedLoginAttempts(0);
        user.setAccountLocked(false);

        userRepository.save(user);
    }
}
