package com.librarymanagement.strategy;

import com.librarymanagement.model.Book;
import com.librarymanagement.model.Loan;
import com.librarymanagement.model.Patron;

import java.util.ArrayList;
import java.util.List;

public class AuthorBasedRecommendation
        implements RecommendationStrategy {

    @Override
    public List<Book> recommendBooks(Patron patron, List<Book> allBooks) {

        List<Book> recommendedBooks = new ArrayList<>();
        List<Loan> history = patron.getBorrowingHistory();

        for (Loan loan : history) {
            String borrowedAuthor = loan.getBook().getAuthor();
            for (Book book : allBooks) {
                if (book.getAuthor().equalsIgnoreCase(borrowedAuthor)) {
                    recommendedBooks.add(book);
                }
            }
        }

        return recommendedBooks;
    }
}