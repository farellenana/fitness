package com.example.fitness.repository;

import com.example.fitness.model.Objectifs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObjectifsRepository extends JpaRepository<Objectifs, Long> {
    List<Objectifs> findByUserId(Long userID);
}
