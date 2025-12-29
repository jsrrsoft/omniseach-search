package com.omniseach.omniseachsearchservice.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.omniseach.omniseachsearchservice.model.RefreshToken;

public interface RefreshTokenRepository
        extends MongoRepository<RefreshToken, String> {

    Optional<RefreshToken> findByToken(String token);
}
