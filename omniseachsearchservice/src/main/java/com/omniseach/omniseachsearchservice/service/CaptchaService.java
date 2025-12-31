package com.omniseach.omniseachsearchservice.service;

import org.springframework.stereotype.Service;

@Service
public class CaptchaService {

    public boolean verify(String captchaToken) {
        // Later integrate Google reCAPTCHA
        return captchaToken != null && !captchaToken.isBlank();
    }
}
