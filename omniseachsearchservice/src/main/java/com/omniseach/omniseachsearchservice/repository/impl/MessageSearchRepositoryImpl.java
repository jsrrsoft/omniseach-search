package com.omniseach.omniseachsearchservice.repository.impl;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.omniseach.omniseachsearchservice.dto.PagedResponse;
import com.omniseach.omniseachsearchservice.model.Message;
import com.omniseach.omniseachsearchservice.repository.MessageSearchRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MessageSearchRepositoryImpl implements MessageSearchRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public PagedResponse<Message> search(String query, int page, int size) {

        Query mongoQuery = new Query();
        mongoQuery.addCriteria(Criteria.where("content").regex(query, "i"));

        long total = mongoTemplate.count(mongoQuery, Message.class);

        mongoQuery.skip((long) page * size);
        mongoQuery.limit(size);

        List<Message> messages =
                mongoTemplate.find(mongoQuery, Message.class);

        return new PagedResponse<>(messages, page, size, total);
    }
}
