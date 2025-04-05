package com.ericpinto.bookshopservice.application.service;

import com.ericpinto.bookshopservice.application.dto.request.BookRequest;
import com.ericpinto.bookshopservice.application.dto.response.BookResponse;
import com.ericpinto.bookshopservice.application.exception.ObjectNotFoundException;
import com.ericpinto.bookshopservice.domain.model.Book;
import com.ericpinto.bookshopservice.domain.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookResponse register(BookRequest bookRequest) {
       Book book = Book.save(bookRequest.title(), bookRequest.author(), bookRequest.gender(), bookRequest.year());

       bookRepository.save(book);

       return toResponse(book);
    }

    public BookResponse findById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Book not found"));
        return toResponse(book);
    }

    public Page<BookResponse> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("title").ascending());
        return bookRepository.findAll(pageable).map(this::toResponse);
    }

    public void rent(Long id) {
        Book book = toModel(findById(id));

        if (book.getIsRented()){
            throw new RuntimeException("Rent already rated");
        }

        book.setIsRented(true);

        bookRepository.save(book);
    }

    public void deleteById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Book not found"));

        if (book.getIsRented()){
            throw new ObjectNotFoundException("Rented book is not available");
        }

        bookRepository.delete(book);
    }

    private Book toModel(BookResponse bookResponse) {
        return Book.builder()
                .id(bookResponse.id())
                .title(bookResponse.title())
                .author(bookResponse.author())
                .gender(bookResponse.gender())
                .year(bookResponse.year())
                .build();
    }

    private BookResponse toResponse(Book book) {
        return new BookResponse(book.getId(), book.getTitle(), book.getAuthor(), book.getGender(), book.getYear());
    }
}
