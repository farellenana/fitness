package com.example.fitness.repository;

import com.example.fitness.model.SeanceEntrainement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeanceEntrainementRepository extends JpaRepository<SeanceEntrainement , Long> {
}
