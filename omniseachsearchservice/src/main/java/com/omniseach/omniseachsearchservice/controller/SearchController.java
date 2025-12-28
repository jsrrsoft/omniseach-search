package com.omniseach.omniseachsearchservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.omniseach.omniseachsearchservice.service.GlobalSearchService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class SearchController {

    private final GlobalSearchService searchService;

    @GetMapping
    public ResponseEntity<?> globalSearch(@RequestParam("q") String query) {
        return ResponseEntity.ok(searchService.searchEverything(query));
    }
}
