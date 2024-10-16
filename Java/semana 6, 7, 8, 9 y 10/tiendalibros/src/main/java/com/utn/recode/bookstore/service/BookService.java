package com.utn.recode.bookstore.service;

import com.utn.recode.bookstore.modelo.Book;
import com.utn.recode.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements IlBookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book searchById(Integer id) {
        Book book = bookRepository.findById(id).orElse(null);
        return book;
    }

    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Override
    public void delete(Book book) {
        bookRepository.delete(book);
    }
}
