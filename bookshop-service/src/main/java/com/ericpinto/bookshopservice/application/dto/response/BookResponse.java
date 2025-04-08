package com.ericpinto.bookshopservice.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Modelo que representa o objeto de retorno ao criar/buscar livros.")
public record BookResponse(
        @Schema(description = "Id do livro.")
        Long id,
        @Schema(description = "Título do Livro", example = "Psicopatologia")
        String title,
        @Schema(description = "Autor do Livro", example = "Paulo")
        String author,
        @Schema(description = "Gênero do Livro", example = "Estudos")
        String gender,
        @Schema(description = "Ano do livro", example = "2005")
        Integer year,
        @Schema(description = "Livro está alugado", example = "false")
        Boolean isRented
) {
}
