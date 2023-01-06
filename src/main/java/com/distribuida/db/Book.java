package com.distribuida.db;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Book {
    @Getter @Setter private int id;
    @Getter @Setter private String isbn;
    @Getter @Setter private String title;
    @Getter @Setter private String author;
    @Getter @Setter private int price;

    public Book(int id, String isbn, String title, String author, int price) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public Book() {
    }
}

