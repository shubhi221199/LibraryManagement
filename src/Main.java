import com.librarymanagement.enums.BookStatus;
import com.librarymanagement.model.Book;
import com.librarymanagement.model.Patron;
import com.librarymanagement.model.Loan;
import com.librarymanagement.repository.BookRepository;
import com.librarymanagement.repository.LoanRepository;
import com.librarymanagement.repository.PatronRepository;
import com.librarymanagement.service.BookService;
import com.librarymanagement.service.LendingService;
import com.librarymanagement.service.PatronService;
import com.librarymanagement.service.RecommendationService;
import com.librarymanagement.strategy.AuthorBasedRecommendation;
import com.librarymanagement.strategy.RecommendationStrategy;
import com.librarymanagement.observer.BookNotifier;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        BookNotifier bookNotifier = new BookNotifier();

        // Repositories

        BookRepository bookRepository = new BookRepository();
        PatronRepository patronRepository = new PatronRepository();
        LoanRepository loanRepository = new LoanRepository();

        // Services

        BookService bookService = new BookService(bookRepository);
        PatronService patronService = new PatronService(patronRepository);
        LendingService lendingService = new LendingService(bookRepository, patronRepository, loanRepository, bookNotifier);

        RecommendationStrategy strategy = new AuthorBasedRecommendation();
        RecommendationService recommendationService = new RecommendationService(strategy);


        boolean running = true;

        while (running) {

            System.out.println("\n===== LIBRARY MANAGEMENT SYSTEM =====");

            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search Book By ISBN");
            System.out.println("4. Add Patron");
            System.out.println("5. Checkout Book");
            System.out.println("6. Return Book");
            System.out.println("7. View Patron Borrowing History");
            System.out.println("8. Exit");
            System.out.println("9. Recommend Books");

            System.out.print("\nEnter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            try {

                switch (choice) {
                    case 1:

                        // Add Book

                        System.out.print("Enter title: ");
                        String title = scanner.nextLine();

                        System.out.print("Enter author: ");
                        String author = scanner.nextLine();

                        System.out.print("Enter ISBN: ");
                        String isbn = scanner.nextLine();

                        System.out.print("Enter publication year: ");
                        int year = scanner.nextInt();
                        scanner.nextLine();

                        Book book = new Book(title, author, isbn, year, BookStatus.AVAILABLE);

                        bookService.addBook(book);

                        break;

                    case 2:

                        // View All Books

                        List<Book> books = bookService.getAllBooks();

                        System.out.println("\n===== BOOK LIST =====");

                        for (Book b : books) {
                            System.out.println(b);
                        }

                        break;

                    case 3:

                        // Search Book

                        System.out.print("Enter ISBN: ");

                        String searchIsbn = scanner.nextLine();

                        Book foundBook = bookService.searchByIsbn(searchIsbn);

                        if (foundBook != null) {
                            System.out.println(foundBook);
                        } else {
                            System.out.println("Book not found.");
                        }

                        break;

                    case 4:

                        // Add Patron

                        System.out.print("Enter patron ID: ");
                        String patronId = scanner.nextLine();

                        System.out.print("Enter name: ");
                        String name = scanner.nextLine();

                        System.out.print("Enter email: ");
                        String email = scanner.nextLine();

                        System.out.print("Enter phone number: ");
                        String phone = scanner.nextLine();

                        Patron patron = new Patron(patronId, name, email, phone);

                        patronService.addPatron(patron);

                        break;

                    case 5:

                        // Checkout Book

                        System.out.print("Enter ISBN: ");
                        String checkoutIsbn = scanner.nextLine();

                        System.out.print("Enter Patron ID: ");
                        String checkoutPatronId = scanner.nextLine();

                        lendingService.checkoutBook(checkoutIsbn, checkoutPatronId);

                        break;

                    case 6:

                        // Return Book

                        System.out.print("Enter Loan ID: ");
                        String loanId = scanner.nextLine();

                        lendingService.returnBook(loanId);

                        break;

                    case 7:

                        // Borrowing History

                        System.out.print("Enter Patron ID: ");
                        String historyPatronId = scanner.nextLine();

                        List<Loan> history = patronService.getBorrowingHistory(historyPatronId);

                        if (history != null) {

                            System.out.println("\n===== BORROWING HISTORY =====");

                            for (Loan loan : history) {
                                System.out.println(loan);
                            }
                        }

                        break;

                    case 8:

                        running = false;

                        System.out.println("Exiting application...");

                        break;
                    case 9:

                        System.out.print("Enter Patron ID: ");

                        String recommendationPatronId = scanner.nextLine();

                        Patron recommendationPatron = patronService.findPatronById(recommendationPatronId);

                        if (recommendationPatron == null) {
                            System.out.println("Patron not found.");
                            break;
                        }
                        List<Book> recommendedBooks = recommendationService.recommendBooks(recommendationPatron, bookService.getAllBooks());
                        System.out.println("\n===== RECOMMENDED BOOKS =====");

                        for (Book recommendedBook : recommendedBooks) {
                            System.out.println(recommendedBook);
                        }

                        break;

                    default:

                        System.out.println("Invalid choice.");
                }
            } catch (RuntimeException e) {

                System.out.println("\nERROR: " + e.getMessage());

            } catch (Exception e) {

                System.out.println("\nSomething went wrong.");
            }
        }

        scanner.close();
    }
}