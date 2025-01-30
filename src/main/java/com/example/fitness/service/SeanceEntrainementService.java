package com.example.fitness.service;

import com.example.fitness.dto.SeanceEntrainementDTO;
import com.example.fitness.mapper.SeanceEntrainementMapper;
import com.example.fitness.model.PlanEntrainement;
import com.example.fitness.model.SeanceEntrainement;
import com.example.fitness.model.User;
import com.example.fitness.repository.PlanEntrainementRepository;
import com.example.fitness.repository.SeanceEntrainementRepository;
import com.example.fitness.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeanceEntrainementService {

    private final SeanceEntrainementRepository seanceEntrainementRepository;
    private final PlanEntrainementRepository planEntrainementRepository;
    private final UserRepository userRepository;
    private final SeanceEntrainementMapper seanceEntrainementMapper;


    public SeanceEntrainementService(SeanceEntrainementRepository seanceEntrainementRepository, PlanEntrainementRepository planEntrainementRepository, UserRepository userRepository, SeanceEntrainementMapper seanceEntrainementMapper) {
        this.seanceEntrainementRepository = seanceEntrainementRepository;
        this.planEntrainementRepository = planEntrainementRepository;
        this.userRepository = userRepository;
        this.seanceEntrainementMapper = seanceEntrainementMapper;
    }

    // Ajouter une Séance d'Entrainement
    public SeanceEntrainementDTO addSeanceEntrainement(SeanceEntrainementDTO seanceEntrainementDTO) {
        // Trouver l'utilisateur
        Optional<User> user = userRepository.findById(seanceEntrainementDTO.getUserID());
        if (!user.isPresent()) {
            throw new IllegalArgumentException("Utilisateur non trouvé avec l'ID : " + seanceEntrainementDTO.getUserID());
        }

        // Trouver le Plan d'Entrainement
        Optional<PlanEntrainement> planEntrainement = planEntrainementRepository.findById(seanceEntrainementDTO.getPlanID());
        if (!planEntrainement.isPresent()) {
            throw new IllegalArgumentException("Plan d'entraînement non trouvé avec l'ID : " + seanceEntrainementDTO.getPlanID());
        }

        // Créer l'entité SeanceEntrainement
        SeanceEntrainement seanceEntrainement = seanceEntrainementMapper.toEntity(seanceEntrainementDTO);
        seanceEntrainement.setUser(user.get());
        seanceEntrainement.setPlanEntrainement(planEntrainement.get());

        // Sauvegarder la Séance d'Entrainement
        SeanceEntrainement savedSeance = seanceEntrainementRepository.save(seanceEntrainement);

        // Retourner le DTO
        return seanceEntrainementMapper.toDto(savedSeance);
    }

    // Obtenir toutes les Séances d'Entrainement
    public List<SeanceEntrainementDTO> getAllSeances() {
        return seanceEntrainementRepository.findAll().stream()
                .map(seanceEntrainementMapper::toDto)
                .toList();
    }

    // Obtenir une Séance d'Entrainement par ID
    public SeanceEntrainementDTO getSeanceById(Long seanceID) {
        Optional<SeanceEntrainement> seanceEntrainement = seanceEntrainementRepository.findById(seanceID);
        if (seanceEntrainement.isPresent()) {
            return seanceEntrainementMapper.toDto(seanceEntrainement.get());
        } else {
            throw new IllegalArgumentException("Séance d'entraînement non trouvée avec l'ID : " + seanceID);
        }
    }

    // Mettre à jour une Séance d'Entrainement
    public SeanceEntrainementDTO updateSeanceEntrainement(Long seanceID, SeanceEntrainementDTO seanceEntrainementDTO) {
        Optional<SeanceEntrainement> existingSeance = seanceEntrainementRepository.findById(seanceID);
        if (existingSeance.isPresent()) {
            SeanceEntrainement seanceEntrainement = existingSeance.get();

            // Mettre à jour la Séance
            seanceEntrainement.setDateSeance(seanceEntrainementDTO.getDateSeance());
            seanceEntrainement.setTypeSeance(seanceEntrainementDTO.getTypeSeance());
            seanceEntrainement.setDuree(seanceEntrainementDTO.getDuree());
            seanceEntrainement.setCaloriesBrulees(seanceEntrainementDTO.getCaloriesBrulees());
            seanceEntrainement.setCommentaires(seanceEntrainementDTO.getCommentaires());

            // Sauvegarder la Séance mise à jour
            SeanceEntrainement updatedSeance = seanceEntrainementRepository.save(seanceEntrainement);

            return seanceEntrainementMapper.toDto(updatedSeance);
        } else {
            throw new IllegalArgumentException("Séance d'entraînement non trouvée avec l'ID : " + seanceID);
        }
    }

    // Supprimer une Séance d'Entrainement
    public void deleteSeance(Long seanceID) {
        if (seanceEntrainementRepository.existsById(seanceID)) {
            seanceEntrainementRepository.deleteById(seanceID);
        } else {
            throw new IllegalArgumentException("Séance d'entraînement non trouvée avec l'ID : " + seanceID);
        }
    }
}
