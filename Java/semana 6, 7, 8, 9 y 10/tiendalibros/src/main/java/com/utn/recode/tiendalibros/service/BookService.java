package com.utn.recode.tiendalibros.service;

import com.utn.recode.tiendalibros.modelo.Book;
import com.utn.recode.tiendalibros.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements IlBookService {
    @Autowired
    private BookRepository libroRepositorio;

    @Override
    public List<Book> getAll() {
        return libroRepositorio.findAll();
    }

    @Override
    public Book searchById(Integer id) {
        Book book = libroRepositorio.findById(id).orElse(null);
        return book;
    }

    @Override
    public void save(Book book) {
        libroRepositorio.save(book);
    }

    @Override
    public void delete(Book book) {
        libroRepositorio.delete(book);
    }
}
