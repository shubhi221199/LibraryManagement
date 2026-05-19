package com.librarymanagement.model;

import java.util.ArrayList;
import java.util.List;

public class Patron extends Person {

    private String patronId;
    private List<Loan> currentLoans;
    private List<Loan> borrowingHistory;

    public Patron(String patronId, String name, String email, String phoneNumber) {
        super(name, email, phoneNumber);
        this.patronId = patronId;
        this.currentLoans = new ArrayList<>();
        this.borrowingHistory = new ArrayList<>();
    }

    // Getter

    public String getPatronId() {
        return patronId;
    }

    public List<Loan> getCurrentLoans() {
        return currentLoans;
    }

    public List<Loan> getBorrowingHistory() {
        return borrowingHistory;
    }

    // Setter

    public void setPatronId(String patronId) {
        this.patronId = patronId;
    }

    // Utility methods

    public void addLoan(Loan loan) {
        currentLoans.add(loan);
        borrowingHistory.add(loan);
    }

    public void returnLoan(Loan loan) {
        currentLoans.remove(loan);
    }

    @Override
    public String toString() {
        return "Patron{" +
                "patronId='" + patronId + '\'' +
                ", name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                ", currentLoans=" + currentLoans.size() +
                '}';
    }
}