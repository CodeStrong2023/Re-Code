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
    private JTable bookBoard;
    private JTextField bookAuthor;
    private JTextField bookPrice;
    private JTextField bookStock;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JTextField bookName;
    private JPanel panel;

    private DefaultTableModel bookBoardModel;
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
    private void showMessage(String msn){
        JOptionPane.showMessageDialog(this, msn);
    }

    private void cleanForm(){
        bookName.setText("");
        bookAuthor.setText("");
        bookPrice.setText("");
        bookStock.setText("");
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
    private void addBook(){
        if(bookName.getText().equals("")){
            showMessage("text: book name");
            bookName.requestFocusInWindow();
            return;
        }
        String name = bookName.getText();
        String author = bookAuthor.getText();
        double price = Double.parseDouble(bookPrice.getText());
        int stock = Integer.parseInt(bookStock.getText());
        Book book = new Book(null, name, author, price, stock);
        this.bookService.save(book);
        showMessage("Se agrego el libro...");
        cleanForm();
        getAllBooks();
    }
    @Autowired
    public BookForm(BookService bookService) {
        this.bookService = bookService;
        createUIComponents();
        format();
        addButton.addActionListener(e -> addBook());
    }

    private void createUIComponents() {
        bookBoardModel = new DefaultTableModel(0, 5);
        String[] head = {"Id", "Libro", "Autor", "Precio", "Existencias"};
        bookBoardModel.setColumnIdentifiers(head);
        this.bookBoard = new JTable(bookBoardModel);
        getAllBooks();
    }
}
