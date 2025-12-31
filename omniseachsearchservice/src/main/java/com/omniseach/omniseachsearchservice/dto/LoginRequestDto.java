package com.omniseach.omniseachsearchservice.dto;

import lombok.Data;

@Data
public class LoginRequestDto {

    private String username;
    private String password;
    private String captcha;
}
