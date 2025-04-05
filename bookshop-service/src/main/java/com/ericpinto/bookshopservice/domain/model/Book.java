package com.ericpinto.bookshopservice.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "books")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private String gender;
    @Column(nullable = false)
    private Integer year;
    private Boolean isRented;

    public void rent() {
        this.isRented = true;
    }

    public void unRent() {
        this.isRented = false;
    }

    public static Book save(String title, String author, String gender, Integer year) {
        return Book.builder()
                .title(title)
                .author(author)
                .gender(gender)
                .year(year)
                .isRented(false)
                .build();
    }
}
