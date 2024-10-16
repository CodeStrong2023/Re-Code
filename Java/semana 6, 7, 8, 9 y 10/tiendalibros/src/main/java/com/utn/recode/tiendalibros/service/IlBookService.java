package com.utn.recode.tiendalibros.service;

import com.utn.recode.tiendalibros.modelo.Book;

import java.util.List;

public interface IlBookService {
    public List<Book> getAll();

    public Book searchById(Integer id);

    public void save (Book book);

    public void delete(Book book);
}
