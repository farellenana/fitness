package com.example.fitness.repository;

import com.example.fitness.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
//    intergagie avec la base de données via des méthodes comme findByEmail(). pouvoir chercher un utilisateur par son adresse email
}
