package com.utn.recode.bookstore.repository;

import com.utn.recode.bookstore.modelo.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}