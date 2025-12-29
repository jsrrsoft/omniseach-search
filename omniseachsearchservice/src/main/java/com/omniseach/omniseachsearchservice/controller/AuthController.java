package com.omniseach.omniseachsearchservice.controller;

import java.time.Instant;
import java.util.UUID;

import org.springframework.web.bind.annotation.*;

import com.omniseach.omniseachsearchservice.dto.AuthResponse;
import com.omniseach.omniseachsearchservice.model.RefreshToken;
import com.omniseach.omniseachsearchservice.repository.RefreshTokenRepository;
import com.omniseach.omniseachsearchservice.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtil jwtUtil;
    private final RefreshTokenRepository refreshRepo;

    @PostMapping("/login")
    public AuthResponse login(@RequestParam String userId) {

        String accessToken = jwtUtil.generateToken(userId);

        RefreshToken refresh = new RefreshToken();
        refresh.setUserId(userId);
        refresh.setToken(UUID.randomUUID().toString());
        refresh.setExpiry(Instant.now().plusSeconds(7 * 86400));

        refreshRepo.save(refresh);

        return new AuthResponse(accessToken, refresh.getToken());
    }

    @PostMapping("/refresh")
    public AuthResponse refresh(@RequestParam String refreshToken) {

        RefreshToken token = refreshRepo.findByToken(refreshToken)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        if (token.getExpiry().isBefore(Instant.now())) {
            throw new RuntimeException("Refresh token expired");
        }

        String newAccessToken =
                jwtUtil.generateToken(token.getUserId());

        return new AuthResponse(newAccessToken, refreshToken);
    }
}
