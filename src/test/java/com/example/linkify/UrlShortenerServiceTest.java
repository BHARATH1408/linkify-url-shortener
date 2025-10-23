package com.example.linkify;

import com.example.linkify.exception.ResourceExpiredException;
import com.example.linkify.model.UrlMapping;
import com.example.linkify.repository.UrlMappingRepository;
import com.example.linkify.service.UrlShortenerService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UrlShortenerServiceTest {

    @Autowired
    private UrlShortenerService service;

    @Autowired
    private UrlMappingRepository repository;

    @AfterEach
    void cleanup() {
        repository.deleteAll();
    }

    @Test
    @Order(1)
    void testCreateShortUrl() {
        UrlMapping mapping = service.createShortUrl("https://spring.io", 2);
        assertNotNull(mapping.getId());
        assertEquals("https://spring.io", mapping.getOriginalUrl());
    }

    @Test
    @Order(2)
    void testRetrieveOriginalUrl() {
        UrlMapping mapping = service.createShortUrl("https://openai.com", null);
        String retrieved = service.getOriginalUrl(mapping.getShortCode());
        assertEquals("https://openai.com", retrieved);
    }

    @Test
    @Order(3)
    void testExpiredUrlThrowsException() {
        String code = "ex" + (System.currentTimeMillis() % 1000000); // safe 8-char code
        repository.saveAndFlush(UrlMapping.builder()
                .shortCode(code)
                .originalUrl("https://expired.com")
                .expiresAt(LocalDateTime.now().minusDays(1))
                .build());

        assertThrows(ResourceExpiredException.class, () -> service.getOriginalUrl(code));
    }
}
