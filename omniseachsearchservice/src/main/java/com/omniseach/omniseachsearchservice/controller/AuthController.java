package com.omniseach.omniseachsearchservice.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.omniseach.omniseachsearchservice.dto.LoginRequestDto;
import com.omniseach.omniseachsearchservice.dto.RegisterRequestDto;
import com.omniseach.omniseachsearchservice.model.RefreshToken;
import com.omniseach.omniseachsearchservice.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /* =========================
       REGISTER
       ========================= */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDto dto) {
        authService.register(dto);
        return ResponseEntity.ok(Map.of("message", "Registration successful. Please verify email."));
    }

    /* =========================
       LOGIN
       ========================= */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto dto) {

        String accessToken = authService.login(dto);
        RefreshToken refreshToken = authService.createRefreshToken(dto.getUsername());

        return ResponseEntity.ok(
                Map.of(
                        "accessToken", accessToken,
                        "refreshToken", refreshToken.getToken()
                )
        );
    }

    /* =========================
       REFRESH TOKEN
       ========================= */
    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestParam String refreshToken) {

        String newAccessToken = authService.refreshAccessToken(refreshToken);

        return ResponseEntity.ok(
                Map.of("accessToken", newAccessToken)
        );
    }

    /* =========================
       VERIFY EMAIL
       ========================= */
    @GetMapping("/verify-email")
    public ResponseEntity<?> verifyEmail(@RequestParam String token) {
        authService.verifyEmail(token);
        return ResponseEntity.ok("Email verified successfully");
    }

    /* =========================
       FORGOT PASSWORD
       ========================= */
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestParam String email) {
        authService.forgotPassword(email);
        return ResponseEntity.ok("Password reset email sent");
    }

    /* =========================
       RESET PASSWORD
       ========================= */
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(
            @RequestParam String token,
            @RequestParam String password
    ) {
        authService.resetPassword(token, password);
        return ResponseEntity.ok("Password reset successful");
    }
}
