package com.ericpinto.bookshopservice.infrastructure.controller;

import com.ericpinto.bookshopservice.application.dto.request.BookRequest;
import com.ericpinto.bookshopservice.application.dto.response.BookResponse;
import com.ericpinto.bookshopservice.application.service.BookService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BookResponse> create(@RequestBody @Valid BookRequest bookRequest) {
        return ResponseEntity.ok(bookService.register(bookRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<BookResponse>> getAll(@RequestParam(defaultValue = "0") Integer page,
                                                     @RequestParam(defaultValue = "10") Integer size) {
        return ResponseEntity.ok(bookService.findAll(page, size));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> rent(@PathVariable Long id) {
        bookService.rent(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookService.deleteById(id);
        return ResponseEntity.noContent().build();
    }



}
