package com.utn.recode.bookstore.view;

import com.utn.recode.bookstore.modelo.Book;
import com.utn.recode.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
@Component
public class BookForm extends  JFrame {

    private final BookService bookService;
    private JPanel panel;
    private JButton deleteButton;
    private JButton editButton;
    private JButton addButton;
    private JTextField textFieldId;
    private JTextField textFieldStock;
    private JTextField textFieldPrice;
    private JTextField textFieldAuthor;
    private JTextField textFieldName;
    private JTable bookTable;
    private DefaultTableModel bookTableModel;

    private void getAllBooks() {
        bookTableModel.setRowCount(0);
        var books = bookService.getAll();
        books.forEach((book) -> {
            Object [] row = {
                    book.getId(),
                    book.getName(),
                    book.getAuthor(),
                    book.getPrice(),
                    book.getStock()
            };
            this.bookTableModel.addRow(row);
        });
    }

    private void createUIComponents() {
        panel = new JPanel(new BorderLayout());
        this.textFieldId = new JTextField("");
        this.textFieldId.setVisible(false);
        this.bookTableModel = new DefaultTableModel(0, 5){
            @Override
            public boolean isCellEditable(int row,int column){
                return false;
            }
        };
        String[] head = {"Id", "Name", "Author", "Price", "Stock"};
        this.bookTableModel.setColumnIdentifiers(head);

        this.bookTable = new JTable(bookTableModel);
        this.bookTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        getAllBooks();
    }
    private void loadBook() {
        var row = bookTable.getSelectedRow();
        if (row != -1) {
            String id = bookTable.getModel().getValueAt(row, 0).toString();
            textFieldId.setText(id);
            String name = bookTable.getModel().getValueAt(row, 1).toString();
            textFieldName.setText(name);
            String author = bookTable.getModel().getValueAt(row, 2).toString();
            textFieldAuthor.setText(author);
            String price = bookTable.getModel().getValueAt(row, 3).toString();
            textFieldPrice.setText(price);
            String stock = bookTable.getModel().getValueAt(row, 4).toString();
            textFieldStock.setText(stock);
        }
    }

    private void cleanTextField() {
        textFieldName.setText("");
        textFieldAuthor.setText("");
        textFieldPrice.setText("");
        textFieldStock.setText("");
    }

    private void showMessage(String msn) {
        JOptionPane.showMessageDialog(this, msn);
    }
    private void addBook() {
        if(textFieldName.getText().equals("")){
            showMessage("insert book name");
            textFieldName.requestFocusInWindow();
            return;
        }
        var name = textFieldName.getText();
        var author = textFieldAuthor.getText();
        var price = Double.parseDouble(textFieldPrice.getText());
        var stock = Integer.parseInt(textFieldStock.getText());
        var book = new Book(null, name, author, price, stock);

        this.bookService.save(book);
        showMessage("add book to...");
        cleanTextField();
        getAllBooks();
    }

    private void deleteBook() {
        var row = bookTable.getSelectedRow();
        if (row != -1) {
            String id = bookTable.getModel().getValueAt(row, 0).toString();
            var book = new Book();
            book.setId(Integer.parseInt(id));
            bookService.delete(book);
            showMessage("book "+id+" deleted");
            cleanTextField();
            getAllBooks();
        }
        else {
            showMessage("must select a book in the table");
        }
    }

    private void editBook() {
        if (this.textFieldId.equals("")){
            showMessage("must select a record in the table");
        }
        else {
            if (textFieldName.getText().equals("")){
                showMessage("insert book name");
                textFieldName.requestFocusInWindow();
                return;
            }
            int id = Integer.parseInt(textFieldId.getText());
            var name = textFieldName.getText();
            var author = textFieldAuthor.getText();
            var price =Double.parseDouble(textFieldPrice.getText());
            var stock = Integer.parseInt(textFieldStock.getText());
            var book = new Book(id, name, author, price, stock);
            bookService.save(book);
            showMessage("to edit book...");
            cleanTextField();
            getAllBooks();
        }
    }

    private void loadForm() {
        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(900,700);
    }

    @Autowired
    public BookForm(BookService bookService) {
        this.bookService = bookService;
        loadForm();
        addButton.addActionListener(e -> addBook());
        bookTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                loadBook();
            }
        });
        editButton.addActionListener(e -> editBook());
        deleteButton.addActionListener(e -> deleteBook());
    }

}

