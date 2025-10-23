package com.example.linkify.service;

import com.example.linkify.exception.ResourceExpiredException;
import com.example.linkify.exception.ResourceNotFoundException;
import com.example.linkify.model.UrlMapping;
import com.example.linkify.repository.UrlMappingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UrlShortenerService {
    private final UrlMappingRepository repository;
    private static final String BASE62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public UrlMapping createShortUrl(String originalUrl, Integer expireDays) {
        String shortCode = generateShortCode();
        LocalDateTime expiresAt = expireDays != null ? LocalDateTime.now().plusDays(expireDays) : null;

        UrlMapping mapping = UrlMapping.builder()
                .shortCode(shortCode)
                .originalUrl(originalUrl)
                .expiresAt(expiresAt)
                .build();

        return repository.save(mapping);
    }

    public String getOriginalUrl(String shortCode) {
        UrlMapping mapping = repository.findByShortCode(shortCode)
                .orElseThrow(() -> new ResourceNotFoundException("Short code not found: " + shortCode));

        if (mapping.getExpiresAt() != null && mapping.getExpiresAt().isBefore(LocalDateTime.now()))
            throw new ResourceExpiredException("Short link expired");

        return mapping.getOriginalUrl();
    }

    private String generateShortCode() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++)
            sb.append(BASE62.charAt(random.nextInt(BASE62.length())));
        return sb.toString();
    }
}
