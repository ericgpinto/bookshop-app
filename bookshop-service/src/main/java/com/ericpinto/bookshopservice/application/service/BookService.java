package com.ericpinto.bookshopservice.application.service;

import com.ericpinto.bookshopservice.application.dto.filter.BookFilterDTO;
import com.ericpinto.bookshopservice.application.dto.request.BookRequest;
import com.ericpinto.bookshopservice.application.dto.response.BookResponse;
import com.ericpinto.bookshopservice.application.exception.BookRentedException;
import com.ericpinto.bookshopservice.application.exception.ObjectNotFoundException;
import com.ericpinto.bookshopservice.domain.model.Book;
import com.ericpinto.bookshopservice.domain.repository.BookRepository;
import com.ericpinto.bookshopservice.infrastructure.persistence.BookSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<BookResponse> findAll(BookFilterDTO filter, Pageable pageable) {
        return bookRepository.findAll(BookSpecification.filter(filter), pageable).map(this::toResponse);
    }

    public void rent(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Book not found"));

        if (book.getIsRented()){
            throw new BookRentedException("Book already rented.");
        }

        book.rent();
        bookRepository.save(book);
    }

    public void unrent(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Book not found"));

        book.unRent();
        bookRepository.save(book);
    }

    public void deleteById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Book not found"));

        if (book.getIsRented()){
            throw new BookRentedException("Book is rented.");
        }

        bookRepository.deleteById(book.getId());
    }

    private BookResponse toResponse(Book book) {
        return new BookResponse(book.getId(), book.getTitle(), book.getAuthor(), book.getGender(), book.getYear(), book.getIsRented());
    }
}
