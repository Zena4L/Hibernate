package com.clement.entity.inheritance;

import jakarta.persistence.Entity;

@Entity
public class Book extends ProductR {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    private String author;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
