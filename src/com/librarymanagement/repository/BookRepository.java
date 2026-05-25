package com.librarymanagement.repository;

import com.librarymanagement.model.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookRepository {

    // isbn -> Book
    private Map<String, Book> books;

    public BookRepository() {
        books = new HashMap<>();
    }

    // Save Book

    public void save(Book book) {
        books.put(book.getIsbn(), book);
    }

    // Find Book By ISBN

    public Book findByIsbn(String isbn) {
        return books.get(isbn);
    }

    // Return All Books

    public List<Book> findAll() {
        return new ArrayList<>(books.values());
    }

    // Delete Book

    public void delete(String isbn) {
        books.remove(isbn);
    }
}