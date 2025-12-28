package com.omniseach.omniseachsearchservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.omniseach.omniseachsearchservice.model.Photo;

public interface PhotoRepository extends MongoRepository<Photo, String> {

    List<Photo> findByTagsInIgnoreCase(List<String> tags);
}
