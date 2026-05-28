package com.librarymanagement.service;

import com.librarymanagement.exception.PatronNotFoundException;
import com.librarymanagement.model.Loan;
import com.librarymanagement.model.Patron;
import com.librarymanagement.repository.PatronRepository;

import java.util.List;
import java.util.logging.Logger;

public class PatronService {

    private PatronRepository patronRepository;

    private static final Logger logger = Logger.getLogger(PatronService.class.getName());

    public PatronService(PatronRepository patronRepository) {

        this.patronRepository = patronRepository;
    }

    // Add Patron

    public void addPatron(Patron patron) {

        if (patron == null) {

            logger.warning("Attempted to add null patron.");

            throw new IllegalArgumentException("Patron cannot be null.");
        }

        if (patron.getPatronId() == null || patron.getPatronId().trim().isEmpty()) {

            logger.warning("Patron ID is empty.");

            throw new IllegalArgumentException("Patron ID cannot be empty.");
        }

        if (patron.getName() == null || patron.getName().trim().isEmpty()) {

            logger.warning("Patron name is empty.");

            throw new IllegalArgumentException("Patron name cannot be empty.");
        }

        Patron existingPatron = patronRepository.findById(patron.getPatronId());

        if (existingPatron != null) {

            logger.warning("Duplicate patron ID detected: " + patron.getPatronId());

            throw new IllegalArgumentException("Patron with same ID already exists.");
        }

        patronRepository.save(patron);

        logger.info("Patron added successfully. " + "Patron ID: " + patron.getPatronId());
    }

    // Remove Patron

    public void removePatron(String patronId) {

        Patron existingPatron = patronRepository.findById(patronId);

        if (existingPatron == null) {

            logger.warning("Patron not found. ID: " + patronId);

            throw new PatronNotFoundException("Patron not found.");
        }

        patronRepository.delete(patronId);

        logger.info("Patron removed successfully. " + "Patron ID: " + patronId);
    }

    // Update Patron

    public void updatePatron(String patronId, String newName, String newEmail, String newPhoneNumber) {

        Patron existingPatron = patronRepository.findById(patronId);

        if (existingPatron == null) {

            logger.warning("Patron not found for update. ID: " + patronId);

            throw new PatronNotFoundException("Patron not found.");
        }

        existingPatron.setName(newName);
        existingPatron.setEmail(newEmail);
        existingPatron.setPhoneNumber(newPhoneNumber);

        logger.info("Patron updated successfully. " + "Patron ID: " + patronId);
    }

    // Find Patron By ID

    public Patron findPatronById(String patronId) {

        Patron patron = patronRepository.findById(patronId);

        if (patron == null) {

            logger.warning("Patron not found. ID: " + patronId);

            throw new PatronNotFoundException("Patron not found.");
        }

        return patron;
    }

    // View Borrowing History

    public List<Loan> getBorrowingHistory(String patronId) {

        Patron patron = patronRepository.findById(patronId);

        if (patron == null) {

            logger.warning("Borrowing history requested for invalid patron. ID: " + patronId);

            throw new PatronNotFoundException("Patron not found.");
        }

        logger.info("Borrowing history fetched for Patron ID: " + patronId);

        return patron.getBorrowingHistory();
    }

    // Get All Patrons

    public List<Patron> getAllPatrons() {

        logger.info("Fetching all patrons.");

        return patronRepository.findAll();
    }
}