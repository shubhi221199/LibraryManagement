package com.librarymanagement.model;

import com.librarymanagement.enums.BookStatus;

import java.util.Objects;

public class Book {

    private String title;
    private String author;
    private String isbn;
    private int publicationYear;
    private BookStatus status;

    public Book(String title, String author, String isbn, int publicationYear, BookStatus status) {

        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.status = status;
    }

    // Getters

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public BookStatus getStatus() {
        return status;
    }

    // Setters

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }


    // Readable object printing

    @Override
    public String toString() {
        return "Book{" + "title='" + title + '\'' + ", author='" + author + '\'' + ", isbn='" + isbn + '\'' + ", publicationYear=" + publicationYear + ", status=" + status + '}';
    }
}