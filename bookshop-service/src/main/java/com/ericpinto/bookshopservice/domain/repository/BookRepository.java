package com.ericpinto.bookshopservice.domain.repository;

import com.ericpinto.bookshopservice.domain.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
