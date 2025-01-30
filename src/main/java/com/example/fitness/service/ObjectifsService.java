package com.example.fitness.service;

import com.example.fitness.dto.ObjectifsDTO;
import com.example.fitness.dto.ProgrammeDTO;
import com.example.fitness.mapper.ObjectifsMapper;
import com.example.fitness.model.Objectifs;
import com.example.fitness.model.Programme;
import com.example.fitness.repository.ObjectifsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ObjectifsService {



    private final ObjectifsRepository objectifsRepository;
    private final ObjectifsMapper objectifsMapper;

    @Autowired
    public ObjectifsService(ObjectifsRepository objectifsRepository, ObjectifsMapper objectifsMapper) {
        this.objectifsRepository = objectifsRepository;
        this.objectifsMapper = objectifsMapper;
    }

    // Ajouter un objectif
    public ObjectifsDTO addObjectif(ObjectifsDTO objectifsDTO) {
        Objectifs objectifs = objectifsMapper.toEntity(objectifsDTO);
        Objectifs savedObjectif = objectifsRepository.save(objectifs);
        return objectifsMapper.toDto(savedObjectif);
    }

    // Mettre à jour un objectif
    public ObjectifsDTO updateObjectif(Long objectifID, ObjectifsDTO objectifsDTO) {
        Optional<Objectifs> existingObjectif = objectifsRepository.findById(objectifID);
        if (existingObjectif.isPresent()) {
            Objectifs objectifs = existingObjectif.get();
            // Mettre à jour les informations de l'objectif
            objectifs.setNomObjectif(objectifsDTO.getNomObjectif());
            objectifs.setDescriptionObjectif(objectifsDTO.getDescriptionObjectif());
            objectifs.setDateDebut(objectifsDTO.getDateDebut());
            objectifs.setDateFin(objectifsDTO.getDateFin());

            // Sauvegarder l'entité mise à jour
            Objectifs updatedObjectif = objectifsRepository.save(objectifs);
            return objectifsMapper.toDto(updatedObjectif);
        }
        throw new IllegalArgumentException("Objectif non trouvé avec l'ID : " + objectifID);
    }

    // Obtenir tous les objectifs
    public List<ObjectifsDTO> getAllObjectifs() {
        List<Objectifs> objectifsList = objectifsRepository.findAll();
        return objectifsList.stream()
                .map(objectifsMapper::toDto)
                .toList();
    }

    // Obtenir un objectif par ID
    public ObjectifsDTO getObjectifById(Long objectifID) {
        Optional<Objectifs> objectifs = objectifsRepository.findById(objectifID);
        if (objectifs.isPresent()) {
            return objectifsMapper.toDto(objectifs.get());
        }
        throw new IllegalArgumentException("Objectif non trouvé avec l'ID : " + objectifID);
    }

    // Supprimer un objectif
    public void deleteObjectif(Long objectifID) {
        if (objectifsRepository.existsById(objectifID)) {
            objectifsRepository.deleteById(objectifID);
        } else {
            throw new IllegalArgumentException("Objectif non trouvé avec l'ID : " + objectifID);
        }
    }

    // Obtenir les objectifs d'un utilisateur spécifique
    public List<ObjectifsDTO> getObjectifsByUser(Long userID) {
        return objectifsRepository.findByUserId(userID)
                .stream()
                .map(objectifsMapper::toDto)
                .toList();
    }
}
