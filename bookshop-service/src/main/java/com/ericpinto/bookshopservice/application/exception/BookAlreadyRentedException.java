package com.ericpinto.bookshopservice.application.exception;

public class BookAlreadyRentedException extends RuntimeException {
    public BookAlreadyRentedException(String message) {
        super(message);
    }
}
