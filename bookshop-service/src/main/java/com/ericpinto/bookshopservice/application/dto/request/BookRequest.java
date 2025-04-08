package com.ericpinto.bookshopservice.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Modelo que representa os campos necessários para cadastra novo livro.")
public record BookRequest(
        @NotBlank
        @Schema(description = "Título do Livro", example = "Psicopatologia")
        String title,
        @NotBlank
        @Schema(description = "Autor do Livro", example = "Paulo")
        String author,
        @NotBlank
        @Schema(description = "Gênero do Livro", example = "Estudos")
        String gender,
        @NotNull
        @Schema(description = "Ano do livro", example = "2005")
        Integer year) {
}
