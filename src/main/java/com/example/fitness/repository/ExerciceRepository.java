package com.example.fitness.repository;

import com.example.fitness.model.Exercice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExerciceRepository extends JpaRepository<Exercice, Long> {

    Optional<Exercice> findById(Long id);

    Optional<Exercice> findByNomExercice(String nomExercice);

//    List<Exercice> findByCategoryExercice(Long id);

//    List<Exercice>  findByProgramme_ProgrammeID(Long programmeID);
}
