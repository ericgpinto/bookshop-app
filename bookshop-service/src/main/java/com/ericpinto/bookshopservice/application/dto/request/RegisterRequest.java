package com.ericpinto.bookshopservice.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Modelo que representa os campos necessário para cadastro de usuário.")
public record RegisterRequest(
        @NotNull @NotBlank
        @Schema(description = "Nome de usuário", example = "user1@")
        String username,
        @NotNull @NotBlank
        @Schema(description = "Senha")
        String password) {
}
