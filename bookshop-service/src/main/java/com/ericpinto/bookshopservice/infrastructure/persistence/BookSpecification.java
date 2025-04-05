package com.ericpinto.bookshopservice.infrastructure.persistence;

import com.ericpinto.bookshopservice.application.dto.BookFilterDTO;
import com.ericpinto.bookshopservice.domain.model.Book;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {

    public static Specification<Book> filter(BookFilterDTO filter) {
        return (root, query, cb) -> {
            var predicate = cb.conjunction();

            if (filter.title() != null && !filter.title().isBlank()) {
                predicate = cb.and(predicate,
                        cb.like(cb.lower(root.get("title")), "%" + filter.title().toLowerCase() + "%"));
            }

            if (filter.author() != null && !filter.author().isBlank()) {
                predicate = cb.and(predicate,
                        cb.like(cb.lower(root.get("author")), "%" + filter.author().toLowerCase() + "%"));
            }

            if (filter.gender() != null && !filter.gender().isBlank()) {
                predicate = cb.and(predicate,
                        cb.like(cb.lower(root.get("gender")), "%" + filter.gender().toLowerCase() + "%"));
            }

            if (filter.year() != null) {
                predicate = cb.and(predicate, cb.equal(root.get("year"), filter.year()));
            }

            return predicate;
        };
    }
}
