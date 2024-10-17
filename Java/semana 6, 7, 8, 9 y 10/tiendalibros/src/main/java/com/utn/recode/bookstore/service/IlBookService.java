package com.utn.recode.bookstore.service;

import com.utn.recode.bookstore.modelo.Book;

import java.util.List;

public interface IlBookService {
    public List<Book> getAll();

    public Book searchById(Integer id);

    public void save (Book book);

    public void delete(Book book);
}
