package com.omniseach.omniseachsearchservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "users")
public class User {

    @Id
    private String id;

    private String fullName;
    private String username;
    private String email;
    private String passwordHash;

    private boolean emailVerified = false;
    private String verificationToken;

    private int failedLoginAttempts = 0;
    private boolean accountLocked = false;

    private String role = "USER";
}

