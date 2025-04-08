package com.ericpinto.bookshopservice.application.service;

import com.ericpinto.bookshopservice.application.dto.request.BookRequest;
import com.ericpinto.bookshopservice.application.dto.response.BookResponse;
import com.ericpinto.bookshopservice.application.exception.BookRentedException;
import com.ericpinto.bookshopservice.application.exception.ObjectNotFoundException;
import com.ericpinto.bookshopservice.domain.model.Book;
import com.ericpinto.bookshopservice.domain.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    Book book;
    Book book2;
    BookRequest request;
    BookResponse response;

    @BeforeEach
    public void setup(){
        book = Book.builder()
                .id(1L)
                .title("Sapiens")
                .author("Yuval")
                .gender("Biografia")
                .year(2011)
                .isRented(false)
                .build();

        book2 = Book.builder()
                .id(2L)
                .title("Psicopatologia")
                .author("Paulo")
                .gender("Estudos")
                .year(2005)
                .isRented(true)
                .build();

        request = new BookRequest("Sapiens", "Yuval", "Biografia", 2011);
        response = new BookResponse(1L, "Sapiens", "Yuval", "Biografia", 2011, false);
    }

    @Test
    void shouldCreateBook(){
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        BookResponse bookResponse = bookService.register(request);

        assertNotNull(bookResponse);
        assertFalse(bookResponse.isRented());
    }

    @Test
    void shouldThrowExceptionWhenBookNotFound(){
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());
        Exception exception = assertThrows(ObjectNotFoundException.class, () -> bookService.findById(1L));

        assertEquals("Book not found", exception.getMessage());
    }

    @Test
    void shouldGetById(){
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        BookResponse bookResponse = bookService.findById(1L);
        assertNotNull(bookResponse);

        assertEquals(response.author(), bookResponse.author());
        assertEquals(response.title(), bookResponse.title());
        assertEquals(response.gender(), bookResponse.gender());
        assertEquals(response.year(), bookResponse.year());
        assertFalse(bookResponse.isRented());
    }

    @Test
    void shouldThrowExceptionWhenBookAlreadyRented(){
        when(bookRepository.findById(2L)).thenReturn(Optional.of(book2));
        Exception exception = assertThrows(BookRentedException.class, () -> bookService.rent(2L));

        assertEquals("Book already rented.", exception.getMessage());
    }

    @Test
    void shouldRentAnBook(){
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        bookService.rent(1L);

        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    void shouldThrowExceptionWhenTryDeleteRentedBook(){
        when(bookRepository.findById(2L)).thenReturn(Optional.of(book2));
        Exception exception = assertThrows(BookRentedException.class, () -> bookService.deleteById(2L));

        assertEquals("Book is rented.", exception.getMessage());
    }

    @Test
    void shouldDeleteBook(){
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        bookService.deleteById(1L);

        verify(bookRepository, times(1)).deleteById(1L);
    }

}