package com.example.fitness.repository;

import com.example.fitness.model.PlanEntrainement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanEntrainementRepository   extends JpaRepository<PlanEntrainement, Long> {
}
