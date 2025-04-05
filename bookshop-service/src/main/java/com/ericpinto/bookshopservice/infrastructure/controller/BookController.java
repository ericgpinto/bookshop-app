package com.ericpinto.bookshopservice.infrastructure.controller;

import com.ericpinto.bookshopservice.application.dto.BookFilterDTO;
import com.ericpinto.bookshopservice.application.dto.request.BookRequest;
import com.ericpinto.bookshopservice.application.dto.response.BookResponse;
import com.ericpinto.bookshopservice.application.service.BookService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public ResponseEntity<Page<BookResponse>> getAll( @ModelAttribute BookFilterDTO filter,
                                                      @PageableDefault(size = 10, sort = "title") Pageable pageable) {
        return ResponseEntity.ok(bookService.findAll(filter, pageable));
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
