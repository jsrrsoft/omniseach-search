package com.omniseach.omniseachsearchservice.service;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.omniseach.omniseachsearchservice.repository.MessageRepository;
import com.omniseach.omniseachsearchservice.repository.PhotoRepository;
import com.omniseach.omniseachsearchservice.repository.PostRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class GlobalSearchService {

    private final MessageRepository messageRepository;
    private final PostRepository postRepository;
    private final PhotoRepository photoRepository;

    public Map<String, Object> searchEverything(String query) {

        Map<String, Object> result = new HashMap<>();

        result.put("messages",
            messageRepository.findByContentContainingIgnoreCase(query));

        result.put("posts",
            postRepository.findByTextContainingIgnoreCase(query));

        result.put("photos",
            photoRepository.findByTagsInIgnoreCase(List.of(query)));

        return result;
    }
}
