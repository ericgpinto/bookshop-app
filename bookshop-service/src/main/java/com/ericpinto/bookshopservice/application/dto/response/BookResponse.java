package com.ericpinto.bookshopservice.application.dto.response;

public record BookResponse(Long id, String title, String author, String gender, Integer year) {
}
