package com.utn.recode.tiendalibros.repository;

import com.utn.recode.tiendalibros.modelo.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}