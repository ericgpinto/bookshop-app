package com.ericpinto.bookshopservice.infrastructure.controller;

import com.ericpinto.bookshopservice.application.dto.filter.BookFilterDTO;
import com.ericpinto.bookshopservice.application.dto.request.BookRequest;
import com.ericpinto.bookshopservice.application.dto.response.BookResponse;
import com.ericpinto.bookshopservice.application.exception.BookRentedException;
import com.ericpinto.bookshopservice.application.exception.ObjectNotFoundException;
import com.ericpinto.bookshopservice.application.service.BookService;
import com.ericpinto.bookshopservice.infrastructure.controller.exception.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
@RequestMapping("/books")
@Tag(name = "API de Livros")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @Operation(summary = "Realizar cadastro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Livro cadastrado com sucesso",
                    content = @Content(schema = @Schema(implementation = BookResponse.class))),
            @ApiResponse(responseCode = "400", description = "Campos não devem ser nulos ou estar em branco.")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BookResponse> create(@RequestBody @Valid BookRequest bookRequest) {
        return ResponseEntity.ok(bookService.register(bookRequest));
    }

    @Operation(summary = "Buscar livro pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Livro encontrado com sucesso",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404",
                    description = "Livro não encontrado.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.findById(id));
    }

    @Operation(summary = "Listar livros por páginas e com filtros")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Livros listados com sucesso",
                    content = @Content(schema = @Schema(implementation = BookResponse.class))),
    })
    @GetMapping
    public ResponseEntity<Page<BookResponse>> getAll( @ModelAttribute BookFilterDTO filter,
                                                      @PageableDefault(size = 10, sort = "title") Pageable pageable) {
        return ResponseEntity.ok(bookService.findAll(filter, pageable));
    }

    @Operation(summary = "Alugar livro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Livro alugado com sucesso"),
            @ApiResponse(responseCode = "404",
                    description = "Livro não encontrado.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409",
                    description = "Livro já esta alugado",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @PatchMapping("/{id}/rent")
    public ResponseEntity<Void> rent(@PathVariable Long id) {
        bookService.rent(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Devolver livro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Livros devolvido com sucesso"),
            @ApiResponse(responseCode = "404",
                    description = "Livro não encontrado.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PatchMapping("/{id}/unrent")
    public ResponseEntity<Void> unRent(@PathVariable Long id) {
        bookService.unrent(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Excluir livro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",
                    description = "Livro excluído com sucesso"),
            @ApiResponse(responseCode = "409",
                    description = "Livro está alugado e não pode ser excluído.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
