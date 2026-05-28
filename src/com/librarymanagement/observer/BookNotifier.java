package com.librarymanagement.observer;

import java.util.ArrayList;
import java.util.List;

public class BookNotifier implements Subject {

    private List<Observer> observers;

    public BookNotifier() {
        observers = new ArrayList<>();
    }

    @Override
    public void addObserver(Observer observer) {

        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {

        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {

        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}