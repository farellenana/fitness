package com.example.fitness.service;

import com.example.fitness.dto.PlanEntrainementDTO;
import com.example.fitness.mapper.PlanEntrainementMapper;
import com.example.fitness.model.Objectifs;
import com.example.fitness.model.PlanEntrainement;
import com.example.fitness.repository.ObjectifsRepository;
import com.example.fitness.repository.PlanEntrainementRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanEntrainementService {
    private final PlanEntrainementRepository planEntrainementRepository;
    private final ObjectifsRepository objectifsRepository;
    private final PlanEntrainementMapper planEntrainementMapper;

    public PlanEntrainementService(PlanEntrainementRepository planEntrainementRepository, ObjectifsRepository objectifsRepository, PlanEntrainementMapper planEntrainementMapper) {
        this.planEntrainementRepository = planEntrainementRepository;
        this.objectifsRepository = objectifsRepository;
        this.planEntrainementMapper = planEntrainementMapper;
    }

    // Ajouter un PlanEntrainement
    public PlanEntrainementDTO addPlanEntrainement(PlanEntrainementDTO planEntrainementDTO) {
        // Trouver l'Objectif
        Optional<Objectifs> objectif = objectifsRepository.findById(planEntrainementDTO.getObjectifID());
        if (!objectif.isPresent()) {
            throw new IllegalArgumentException("Objectif non trouvé avec l'ID : " + planEntrainementDTO.getObjectifID());
        }

        // Créer l'entité PlanEntrainement
        PlanEntrainement planEntrainement = planEntrainementMapper.toEntity(planEntrainementDTO);
        planEntrainement.setObjectif(objectif.get());

        // Sauvegarder le PlanEntrainement
        PlanEntrainement savedPlan = planEntrainementRepository.save(planEntrainement);

        // Retourner le DTO
        return planEntrainementMapper.toDto(savedPlan);
    }

    // Obtenir tous les Plans d'Entrainement
    public List<PlanEntrainementDTO> getAllPlans() {
        return planEntrainementRepository.findAll().stream()
                .map(planEntrainementMapper::toDto)
                .toList();
    }

    // Obtenir un Plan d'Entrainement par ID
    public PlanEntrainementDTO getPlanById(Long planID) {
        Optional<PlanEntrainement> planEntrainement = planEntrainementRepository.findById(planID);
        if (planEntrainement.isPresent()) {
            return planEntrainementMapper.toDto(planEntrainement.get());
        } else {
            throw new IllegalArgumentException("Plan d'entraînement non trouvé avec l'ID : " + planID);
        }
    }

    // Mettre à jour un Plan d'Entrainement
    public PlanEntrainementDTO updatePlanEntrainement(Long planID, PlanEntrainementDTO planEntrainementDTO) {
        Optional<PlanEntrainement> existingPlan = planEntrainementRepository.findById(planID);
        if (existingPlan.isPresent()) {
            PlanEntrainement planEntrainement = existingPlan.get();

            // Mettre à jour le Plan
            planEntrainement.setNomPlan(planEntrainementDTO.getNomPlan());
            planEntrainement.setDateDebut(planEntrainementDTO.getDateDebut());
            planEntrainement.setDateFin(planEntrainementDTO.getDateFin());
            planEntrainement.setDescription(planEntrainementDTO.getDescription());

            // Rechercher l'Objectif
            Optional<Objectifs> objectif = objectifsRepository.findById(planEntrainementDTO.getObjectifID());
            if (objectif.isPresent()) {
                planEntrainement.setObjectif(objectif.get());
            }

            // Sauvegarder le Plan mis à jour
            PlanEntrainement updatedPlan = planEntrainementRepository.save(planEntrainement);

            return planEntrainementMapper.toDto(updatedPlan);
        } else {
            throw new IllegalArgumentException("Plan d'entraînement non trouvé avec l'ID : " + planID);
        }
    }

    // Supprimer un Plan d'Entrainement
    public void deletePlan(Long planID) {
        if (planEntrainementRepository.existsById(planID)) {
            planEntrainementRepository.deleteById(planID);
        } else {
            throw new IllegalArgumentException("Plan d'entraînement non trouvé avec l'ID : " + planID);
        }
    }

}
