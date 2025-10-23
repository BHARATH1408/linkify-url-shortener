package com.example.linkify.controller;

import com.example.linkify.dto.UrlRequest;
import com.example.linkify.dto.UrlResponse;
import com.example.linkify.model.UrlMapping;
import com.example.linkify.service.UrlShortenerService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/url")
@RequiredArgsConstructor
public class UrlController {

    private final UrlShortenerService service;

    @PostMapping
    public ResponseEntity<UrlResponse> createShortUrl(@Valid @RequestBody UrlRequest request) {
        UrlMapping mapping = service.createShortUrl(request.getOriginalUrl(), request.getExpireDays());
        return ResponseEntity.ok(new UrlResponse(mapping.getShortCode(), mapping.getOriginalUrl()));
    }

    @GetMapping("/{shortCode}")
    public void redirect(@PathVariable String shortCode, HttpServletResponse response) throws IOException {
        String originalUrl = service.getOriginalUrl(shortCode);
        response.sendRedirect(originalUrl);
    }
}
