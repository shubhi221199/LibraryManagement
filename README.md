# Library Management System

## Overview

The Library Management System is a Java-based console application designed to manage books, patrons, and lending operations within a library. The project demonstrates core Object-Oriented Programming (OOP) concepts, SOLID principles, layered architecture, Java collections, exception handling, logging, and design patterns.

This system allows librarians to:

* Manage books and patrons
* Track book availability
* Check out and return books
* Maintain borrowing history
* Recommend books to patrons
* Notify patrons when books become available

---

# Features

## Book Management

* Add new books
* Remove books
* Update book information
* Search books by:

  * ISBN
  * Title
  * Author

---

## Patron Management

* Add patrons
* Remove patrons
* Update patron details
* Track borrowing history

---

## Lending Management

* Check out books
* Return books
* Track active loans
* Maintain inventory status

---

## Inventory Tracking

Tracks whether books are:

* AVAILABLE
* BORROWED

---

## Recommendation System (Strategy Pattern)

Provides book recommendations based on patron borrowing history.

Current implementation:

* Author-based recommendations

---

## Notification System (Observer Pattern)

Patrons receive notifications when books become available again.

---

## Exception Handling

Custom exceptions implemented for:

* Book not found
* Patron not found
* Loan not found
* Book unavailable
* Duplicate entries

---

## Logging

Implemented using:

```text
java.util.logging.Logger
```

Logs:

* successful operations
* warnings
* invalid actions
* lending operations

---

# Technologies Used

* Java
* OOP Principles
* Java Collections Framework
* Java Logging API

---

# OOP Concepts Used

## Encapsulation

Private fields with getters/setters.

## Inheritance

```text
Person → Patron
```

## Abstraction

Interfaces used for:

* RecommendationStrategy
* Observer
* Subject

## Polymorphism

Implemented using Strategy Pattern.

---

# SOLID Principles Applied

## Single Responsibility Principle (SRP)

Each class has a single responsibility:

* Repository → data storage
* Service → business logic
* Model → data representation

---

## Open/Closed Principle (OCP)

A recommendation system can be extended with new recommendation strategies without modifying existing code.

---

## Dependency Inversion Principle (DIP)

Services depend on abstractions/interfaces instead of concrete implementations.

---

# Design Patterns Used

## 1. Strategy Pattern

Used in the Recommendation System.

### Purpose

Allows switching recommendation algorithms dynamically.

### Classes

* RecommendationStrategy
* AuthorBasedRecommendation
* RecommendationService

---

## 2. Observer Pattern

Used in the Book Notification System.

### Purpose

Notify patrons automatically when books become available.

### Classes

* Observer
* Subject
* BookNotifier

---

# Project Structure

```text
src
│
├── model
│   ├── Book
│   ├── Loan
│   ├── Patron
│   └── Person
│
├── repository
│   ├── BookRepository
│   ├── LoanRepository
│   └── PatronRepository
│
├── service
│   ├── BookService
│   ├── LendingService
│   ├── PatronService
│   └── RecommendationService
│
├── strategy
│   ├── RecommendationStrategy
│   └── AuthorBasedRecommendation
│
├── observer
│   ├── Observer
│   ├── Subject
│   └── BookNotifier
│
├── exception
│   ├── BookNotFoundException
│   ├── BookUnavailableException
│   ├── LoanNotFoundException
│   └── PatronNotFoundException
│
├── enums
│   ├── BookStatus
│   └── LoanStatus
│
└── Main
```

---

# Class Relationships

```text
Person
   ↑
Patron

Patron ---- Loan ---- Book

Services → Repositories → Models
```

---

# Data Structures Used

| Data Structure | Purpose                          |
| -------------- | -------------------------------- |
| Map            | Fast lookup using IDs/ISBN       |
| List           | Store collections of loans/books |
| ArrayList      | Dynamic storage                  |
| HashMap        | Efficient retrieval              |

---

# How To Run

## 1. Clone Repository

```bash
git clone <repository-link>
```

---

## 2. Open Project

Open in:

* IntelliJ IDEA
* Eclipse
* VS Code

---

## 3. Run Application

Run:

```text
Main.java
```

---

# Sample Functionalities

* Add Book
* Add Patron
* Checkout Book
* Return Book
* View Borrowing History
* Search Books
* Get Recommendations
* Receive Notifications

---

# Future Improvements

* Multi-branch support
* Reservation queue system
* Database integration
* REST API support
* GUI/Web Interface
* Advanced recommendation algorithms

---

# Author

Shubhi Sharma

---
