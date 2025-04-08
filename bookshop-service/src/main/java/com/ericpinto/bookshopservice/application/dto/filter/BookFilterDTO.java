package com.ericpinto.bookshopservice.application.dto.filter;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Modelo que representa objeto de filtros na passagem de parâmetros para listagem de livros.")
public record BookFilterDTO(
        @Schema(description = "Título do Livro", example = "Psicopatologia")
        String title,
        @Schema(description = "Autor do Livro", example = "Paulo")
        String author,
        @Schema(description = "Gênero do Livro", example = "Estudos")
        String gender,
        @Schema(description = "Ano do livro", example = "2005")
        Integer year) {
}
