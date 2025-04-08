package com.ericpinto.bookshopservice.infrastructure.controller;

import com.ericpinto.bookshopservice.application.dto.request.AuthRequest;
import com.ericpinto.bookshopservice.application.dto.response.AuthResponse;
import com.ericpinto.bookshopservice.application.dto.request.RegisterRequest;
import com.ericpinto.bookshopservice.application.exception.InvalidCredentialsException;
import com.ericpinto.bookshopservice.application.exception.UsernameAlreadyExistsException;
import com.ericpinto.bookshopservice.application.service.AuthService;
import com.ericpinto.bookshopservice.infrastructure.controller.exception.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "API de Autenticação")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(summary = "Realizar cadastro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Usuário cadastrado com sucesso",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "409",
                    description = "Nome do usuário já está em uso.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))    ,
            @ApiResponse(responseCode = "400", description = "Campos não devem ser nulos ou estar em branco.")
    })
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest registerRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authService.register(registerRequest));
    }

    @Operation(summary = "Realizar Login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Login realizado com sucesso",
                    content = @Content(schema = @Schema(implementation = AuthResponse.class))),
            @ApiResponse(responseCode = "401",
                    description = "Nome ou senha inválidos.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))   ,
            @ApiResponse(responseCode = "400",
                    description = "Campos não devem ser nulos ou estar em branco.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(authService.login(authRequest));
    }

}
