package com.ericpinto.bookshopservice.application.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Modelo que representa o objeto de retorno realizar login.")
public record AuthResponse(
        @Schema(description = "Token de acesso")
        String token) {
}
