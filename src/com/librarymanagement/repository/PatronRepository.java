package com.librarymanagement.repository;

import com.librarymanagement.model.Patron;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatronRepository {

    // patronId -> Patron
    private Map<String, Patron> patrons;

    public PatronRepository() {
        patrons = new HashMap<>();
    }

    // Save Patron

    public void save(Patron patron) {
        patrons.put(patron.getPatronId(), patron);
    }

    // Find Patron By ID

    public Patron findById(String patronId) {
        return patrons.get(patronId);
    }

    // Return All Patrons

    public List<Patron> findAll() {
        return new ArrayList<>(patrons.values());
    }

    // Delete Patron

    public void delete(String patronId) {
        patrons.remove(patronId);
    }
}