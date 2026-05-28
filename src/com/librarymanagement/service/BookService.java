package com.librarymanagement.service;

import com.librarymanagement.exception.BookNotFoundException;
import com.librarymanagement.model.Book;
import com.librarymanagement.repository.BookRepository;

import java.util.logging.Logger;

import java.util.ArrayList;
import java.util.List;

public class BookService {

    private BookRepository bookRepository;
    private static final Logger logger = Logger.getLogger(BookService.class.getName());

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Add Book

    public void addBook(Book book) {

        Book existingBook = bookRepository.findByIsbn(book.getIsbn());

        if (existingBook != null) {
            System.out.println("Book with same ISBN already exists.");
            return;
        }

        bookRepository.save(book);

        logger.info("Book added successfully: " + book.getTitle());
    }

    // Remove Book

    public void removeBook(String isbn) {

        Book existingBook = bookRepository.findByIsbn(isbn);

        if (existingBook == null) {
            logger.warning("Book not found. ");
            return;
        }

        bookRepository.delete(isbn);

        System.out.println("Book removed successfully.");
    }

    // Update Book

    public void updateBook(String isbn, String newTitle, String newAuthor, int newPublicationYear) {

        Book existingBook = bookRepository.findByIsbn(isbn);

        if (existingBook == null) {
            throw new BookNotFoundException("Book not found with ISBN: " + isbn);

        }

        existingBook.setTitle(newTitle);
        existingBook.setAuthor(newAuthor);
        existingBook.setPublicationYear(newPublicationYear);

        System.out.println("Book updated successfully.");
    }

    // Search By ISBN

    public Book searchByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    // Search By Title

    public List<Book> searchByTitle(String title) {

        List<Book> matchedBooks = new ArrayList<>();

        List<Book> allBooks = bookRepository.findAll();

        for (Book book : allBooks) {

            if (book.getTitle().equalsIgnoreCase(title)) {
                matchedBooks.add(book);
            }
        }

        return matchedBooks;
    }

    // Search By Author

    public List<Book> searchByAuthor(String author) {

        List<Book> matchedBooks = new ArrayList<>();

        List<Book> allBooks = bookRepository.findAll();

        for (Book book : allBooks) {

            if (book.getAuthor().equalsIgnoreCase(author)) {
                matchedBooks.add(book);
            }
        }

        return matchedBooks;
    }

    // Get All Books

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}