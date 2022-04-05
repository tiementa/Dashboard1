package com.example.MyTools.repository;

import com.example.MyTools.model.Atelier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AtelierRepository extends JpaRepository<Atelier, Integer> {
    Optional<Atelier> findAtelierByContacts(String contact);
    static Optional<Atelier> findByEmailAndPassword(String email, String password) {
        return null;
    }
    Optional<Atelier> findByEmail(String email);
}
