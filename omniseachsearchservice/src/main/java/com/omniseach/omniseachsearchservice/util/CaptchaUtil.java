package com.omniseach.omniseachsearchservice.util;

import java.util.UUID;

public class CaptchaUtil {

    public static boolean validate(String captcha) {
        // For now: basic validation
        return captcha != null && captcha.length() > 5;
    }

    public static String generate() {
        return UUID.randomUUID().toString().substring(0, 6);
    }
}
