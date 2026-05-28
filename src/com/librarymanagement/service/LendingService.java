package com.librarymanagement.service;

import com.librarymanagement.enums.BookStatus;
import com.librarymanagement.enums.LoanStatus;
import com.librarymanagement.exception.BookNotFoundException;
import com.librarymanagement.exception.BookUnavailableException;
import com.librarymanagement.exception.LoanNotFoundException;
import com.librarymanagement.exception.PatronNotFoundException;
import com.librarymanagement.model.Book;
import com.librarymanagement.model.Loan;
import com.librarymanagement.model.Patron;
import com.librarymanagement.observer.BookNotifier;
import com.librarymanagement.repository.BookRepository;
import com.librarymanagement.repository.LoanRepository;
import com.librarymanagement.repository.PatronRepository;

import java.time.LocalDate;
import java.util.UUID;
import java.util.logging.Logger;

public class LendingService {

    private BookRepository bookRepository;
    private PatronRepository patronRepository;
    private LoanRepository loanRepository;
    private BookNotifier bookNotifier;

    private static final Logger logger = Logger.getLogger(LendingService.class.getName());

    public LendingService(BookRepository bookRepository, PatronRepository patronRepository, LoanRepository loanRepository, BookNotifier bookNotifier) {

        this.bookRepository = bookRepository;
        this.patronRepository = patronRepository;
        this.loanRepository = loanRepository;
        this.bookNotifier = bookNotifier;
    }

    // Checkout Book

    public void checkoutBook(String isbn, String patronId) {

        Book book = bookRepository.findByIsbn(isbn);

        if (book == null) {

            logger.warning("Book not found with ISBN: " + isbn);

            throw new BookNotFoundException("Book not found with ISBN: " + isbn);
        }

        if (book.getStatus() != BookStatus.AVAILABLE) {

            logger.warning("Attempted checkout of unavailable book. ISBN: " + isbn);

            throw new BookUnavailableException("Book is currently not available.");
        }

        Patron patron = patronRepository.findById(patronId);

        if (patron == null) {

            logger.warning("Patron not found. ID: " + patronId);

            throw new PatronNotFoundException("Patron not found.");
        }

        Loan loan = new Loan(UUID.randomUUID().toString(), book, patron, LocalDate.now(), LocalDate.now().plusDays(14), null, LoanStatus.ACTIVE);

        // Update Book Status
        book.setStatus(BookStatus.BORROWED);

        // Add Loan To Patron
        patron.addLoan(loan);

        // Save Loan
        loanRepository.save(loan);

        logger.info("Book checked out successfully. " + "ISBN: " + isbn + ", Patron ID: " + patronId);
    }

    // Return Book

    public void returnBook(String loanId) {

        Loan loan = loanRepository.findById(loanId);

        if (loan == null) {

            logger.warning("Loan not found. ID: " + loanId);

            throw new LoanNotFoundException("Loan not found.");
        }

        if (loan.getStatus() == LoanStatus.RETURNED) {

            logger.warning("Attempted return of already returned book. " + "Loan ID: " + loanId);

            return;
        }

        // Update Loan
        loan.setReturnDate(LocalDate.now());

        loan.setStatus(LoanStatus.RETURNED);

        // Update Book Status
        Book book = loan.getBook();

        book.setStatus(BookStatus.AVAILABLE);

        // Notify Observers
        bookNotifier.notifyObservers(book.getTitle() + " is now available.");

        // Remove Active Loan From Patron
        Patron patron = loan.getPatron();

        patron.returnLoan(loan);

        logger.info("Book returned successfully. " + "Loan ID: " + loanId);
    }
}