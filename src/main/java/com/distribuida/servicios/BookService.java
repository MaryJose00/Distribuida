package com.distribuida.servicios;

import com.distribuida.db.Book;

import java.util.List;

public interface BookService {

    Book findById(Integer id);
    List<Book>findAll();
    String  delete(Integer id);
    Book update(Book book, Integer id);
    Book insert(Book book);


}
