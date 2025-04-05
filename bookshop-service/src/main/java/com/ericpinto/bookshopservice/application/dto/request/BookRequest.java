package com.ericpinto.bookshopservice.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BookRequest(
        @NotBlank
        String title,
        @NotBlank
        String author,
        @NotBlank
        String gender,
        @NotNull
        Integer year) {
}
