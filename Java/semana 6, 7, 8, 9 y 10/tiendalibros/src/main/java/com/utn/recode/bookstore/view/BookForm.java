package com.utn.recode.bookstore.view;

import com.utn.recode.bookstore.modelo.Book;
import com.utn.recode.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

@Component
public class BookForm extends JFrame {
    BookService bookService;
    private JPanel panel;

    private JTable bookBoard;

    private DefaultTableModel bookBoardModel;

    @Autowired
    public BookForm(BookService bookService) {
        this.bookService = bookService;
        format();
    }

    private void format() {
        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(900, 700);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension panelSize = toolkit.getScreenSize();
        setLocation(
                panelSize.width - getWidth() / 2,
                panelSize.height - getHeight() / 2
        );
    }

    private void createUIComponents() {
        bookBoardModel = new DefaultTableModel(0, 5);
        String[] head = {"Id", "Libro", "Autor", "Precio", "Existencias"};
        bookBoardModel.setColumnIdentifiers(head);
        this.bookBoard = new JTable(bookBoardModel);
        getAllBooks();
    }

    private void getAllBooks() {
        bookBoardModel.setRowCount(0);
        List<Book> library = bookService.getAll();
        library.forEach((book) -> {
            Object[] bookFrame = {
                    book.getId(),
                    book.getName(),
                    book.getAuthor(),
                    book.getPrice(),
                    book.getStock()
            };
            bookBoardModel.addRow(bookFrame);
        });
    }

}
