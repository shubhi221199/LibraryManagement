package com.librarymanagement.service;

import com.librarymanagement.model.Book;
import com.librarymanagement.model.Patron;
import com.librarymanagement.strategy.RecommendationStrategy;

import java.util.List;

public class RecommendationService {

    private RecommendationStrategy strategy;

    public RecommendationService(
            RecommendationStrategy strategy
    ) {

        this.strategy = strategy;
    }

    public List<Book> recommendBooks(
            Patron patron,
            List<Book> allBooks
    ) {

        return strategy.recommendBooks(
                patron,
                allBooks
        );
    }
}