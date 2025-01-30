package com.example.fitness.repository;

import com.example.fitness.model.Programme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProgrammeRepository  extends JpaRepository<Programme, Long> {
    // Récupérer un programme par son ID
    Optional<Programme> findById(Long id);

    // Récupérer tous les programmes d'un utilisateur spécifique
    List<Programme> findByUserId(Long userId);

    // Récupérer tous les programmes actifs
    List<Programme> findByEstActif(boolean estActif);

    // Récupérer un programme par son nom
    Optional<Programme> findByNomProgramme(String nomProgramme);

    // Récupérer tous les programmes ayant un objectif spécifique
    List<Programme> findByTypeObjectif(String typeObjectif);
}
