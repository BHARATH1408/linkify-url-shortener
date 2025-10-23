package com.example.linkify.controller;

import com.example.linkify.dto.UrlRequest;
import com.example.linkify.repository.UrlMappingRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UrlControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UrlMappingRepository repository;

    private static String generatedShortCode;

    // ❌ Remove @AfterEach cleanup here

    @Test
    @Order(1)
    void testCreateShortUrl() throws Exception {
        UrlRequest request = new UrlRequest();
        request.setOriginalUrl("https://www.google.com");
        request.setExpireDays(2);

        var result = mockMvc.perform(post("/api/v1/url")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.shortCode").exists())
                .andReturn();

        generatedShortCode = objectMapper.readTree(result.getResponse().getContentAsString())
                .get("shortCode").asText();

        assertThat(repository.findByShortCode(generatedShortCode)).isPresent();
    }

    @Test
    @Order(2)
    void testRedirectShortUrl() throws Exception {
        assertThat(generatedShortCode).isNotNull();
        mockMvc.perform(get("/api/v1/url/" + generatedShortCode))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @Order(3)
    void testExpiredUrlReturnsGone() throws Exception {
        String code = "ex" + (System.currentTimeMillis() % 1000000); // <= 8 chars
        repository.saveAndFlush(com.example.linkify.model.UrlMapping.builder()
                .shortCode(code)
                .originalUrl("https://expired.com")
                .expiresAt(java.time.LocalDateTime.now().minusDays(1))
                .build());

        mockMvc.perform(get("/api/v1/url/" + code))
                .andExpect(status().isGone());
    }

    @Test
    @Order(4)
    void testNonExistingShortCodeReturnsNotFound() throws Exception {
        mockMvc.perform(get("/api/v1/url/notfound"))
                .andExpect(status().isNotFound());
    }
}

