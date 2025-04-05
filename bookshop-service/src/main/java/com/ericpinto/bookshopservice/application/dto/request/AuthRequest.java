package com.ericpinto.bookshopservice.application.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AuthRequest(
        @NotBlank
        String username,
        @NotBlank
        String password) {
}
