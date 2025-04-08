package com.ericpinto.bookshopservice.application.exception;

public class BookRentedException extends RuntimeException {
    public BookRentedException(String message) {
        super(message);
    }
}
