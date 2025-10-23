package com.example.linkify.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UrlResponse {
    private String shortCode;
    private String originalUrl;
}
