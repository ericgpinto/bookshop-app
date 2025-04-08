package com.ericpinto.bookshopservice.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Modelo que representa os campos necessário para realizar login.")
public record AuthRequest(
        @NotBlank
        @Schema(description = "Nome de usuário", example = "user1@")
        String username,
        @NotBlank
        @Schema(description = "Senha criada pelo usuário")
        String password) {
}
