package com.librarymanagement.repository;

import com.librarymanagement.model.Loan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoanRepository {

    // loanId -> Loan
    private Map<String, Loan> loans;

    public LoanRepository() {
        loans = new HashMap<>();
    }

    // Save Loan

    public void save(Loan loan) {
        loans.put(loan.getLoanId(), loan);
    }

    // Find Loan By ID

    public Loan findById(String loanId) {
        return loans.get(loanId);
    }

    // Get All Loans

    public List<Loan> findAll() {
        return new ArrayList<>(loans.values());
    }

    // Delete Loan

    public void delete(String loanId) {
        loans.remove(loanId);
    }
}