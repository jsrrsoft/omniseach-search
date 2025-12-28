package com.omniseach.omniseachsearchservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.omniseach.omniseachsearchservice.model.Post;

public interface PostRepository extends MongoRepository<Post, String> {

    List<Post> findByTextContainingIgnoreCase(String keyword);
}
