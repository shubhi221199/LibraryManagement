package com.librarymanagement.model;

import com.librarymanagement.enums.LoanStatus;

import java.time.LocalDate;

public class Loan {

    private String loanId;
    private Book book;
    private Patron patron;

    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    private LoanStatus status;

    public Loan(String loanId,
                Book book,
                Patron patron,
                LocalDate borrowDate,
                LocalDate dueDate,
                LocalDate returnDate,
                LoanStatus status) {

        this.loanId = loanId;
        this.book = book;
        this.patron = patron;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    // Getters

    public String getLoanId() {
        return loanId;
    }

    public Book getBook() {
        return book;
    }

    public Patron getPatron() {
        return patron;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public LoanStatus getStatus() {
        return status;
    }

    // Setters

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setPatron(Patron patron) {
        this.patron = patron;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "loanId='" + loanId + '\'' +
                ", book=" + book.getTitle() +
                ", patron=" + patron.getName() +
                ", borrowDate=" + borrowDate +
                ", dueDate=" + dueDate +
                ", returnDate=" + returnDate +
                ", status=" + status +
                '}';
    }
}