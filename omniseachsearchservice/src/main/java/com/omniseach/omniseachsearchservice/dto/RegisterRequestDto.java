package com.omniseach.omniseachsearchservice.dto;

import lombok.Data;

@Data
public class RegisterRequestDto {

    private String fullName;
    private String email;
    private String username;
    private String password;
    private String captcha;
}
