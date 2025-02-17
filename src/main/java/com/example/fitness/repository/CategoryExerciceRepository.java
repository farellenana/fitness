package com.example.fitness.repository;

import com.example.fitness.model.CategoryExercice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryExerciceRepository extends JpaRepository<CategoryExercice, Long> {


}
