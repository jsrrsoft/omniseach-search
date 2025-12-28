package com.omniseach.omniseachsearchservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.omniseach.omniseachsearchservice.model.Message;

public interface MessageRepository extends MongoRepository<Message, String> {

    List<Message> findByContentContainingIgnoreCase(String keyword);
}

