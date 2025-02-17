package com.example.fitness.service;

import com.example.fitness.dto.ProgrammeDTO;
import com.example.fitness.mapper.ProgrammeMapper;
import com.example.fitness.model.Objectifs;
import com.example.fitness.model.Programme;
import com.example.fitness.repository.ObjectifsRepository;
import com.example.fitness.repository.ProgrammeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgrammeService {


    private final ProgrammeRepository programmeRepository;
    private final ProgrammeMapper programmeMapper;
    private  final ObjectifsRepository objectifsRepository;

    @Autowired
    public ProgrammeService(ProgrammeRepository programmeRepository, ProgrammeMapper programmeMapper, ObjectifsRepository objectifsRepository) {
        this.programmeRepository = programmeRepository;
        this.programmeMapper = programmeMapper;
        this.objectifsRepository = objectifsRepository;
    }

    // Ajouter un programme
    public ProgrammeDTO addProgramme(ProgrammeDTO programmeDTO) {
        Optional<Objectifs> objectif = objectifsRepository.findById(programmeDTO.getObjectifID());
        if (!objectif.isPresent()) {
            throw new IllegalArgumentException("Objectif non trouvé avec l'ID : " + programmeDTO.getObjectifID());
        }

        Programme programme = programmeMapper.toEntity(programmeDTO);
        programme.setObjectif(objectif.get());
        Programme savedProgramme = programmeRepository.save(programme);
        return programmeMapper.toDto(savedProgramme);
    }

    // Mettre à jour un programme
    public ProgrammeDTO updateProgramme(Long programmeID, ProgrammeDTO programmeDTO) {
        Optional<Programme> existingProgramme = programmeRepository.findById(programmeID);
        if (existingProgramme.isPresent()) {
            Programme programme = existingProgramme.get();
            // Mettre à jour les informations du programme
            programme.setNomProgramme(programmeDTO.getNomProgramme());
            programme.setDescription(programmeDTO.getDescription());
            programme.setDateDebut(programmeDTO.getDateDebut());
            programme.setDateFin(programmeDTO.getDateFin());
            programme.setEstActif(programmeDTO.isEstActif());

//            Optional<Objectifs> objectif = objectifsRepository.findById(programmeDTO.getObjectifID());
//            if (objectif.isPresent()) {
//                programmeDTO.setObjectifID(objectif.get());
//            }

            // Sauvegarder l'entité mise à jour
            Programme updatedProgramme = programmeRepository.save(programme);
            return programmeMapper.toDto(updatedProgramme);
        }
        throw new IllegalArgumentException("Programme not found with ID: " + programmeID);
    }

    // Obtenir tous les programmes
    public List<ProgrammeDTO> getAllProgrammes() {
        List<Programme> programmes = programmeRepository.findAll();
        return programmes.stream()
                .map(programmeMapper::toDto)
                .toList();
    }

    // Obtenir un programme par ID
    public ProgrammeDTO getProgrammeById(Long programmeID) {
        Optional<Programme> programme = programmeRepository.findById(programmeID);
        if (programme.isPresent()) {
            return programmeMapper.toDto(programme.get());
        }
        throw new IllegalArgumentException("Programme not found with ID: " + programmeID);
    }

    // Supprimer un programme
    public void deleteProgramme(Long programmeID) {
        if (programmeRepository.existsById(programmeID)) {
            programmeRepository.deleteById(programmeID);
        } else {
            throw new IllegalArgumentException("Programme not found with ID: " + programmeID);
        }
    }











//    private ProgrammeRepository programmeRepository;
//
//    public ProgrammeService(ProgrammeRepository programmeRepository) {
//        this.programmeRepository = programmeRepository;
//    }
//
//    public Programme insertProgramme(Programme programme) {
//        return programmeRepository.save(programme);
//    }
//
//    // Obtenir un programme par ID
//    public Optional<Programme> getProgrammebyId(String id) {
//        return programmeRepository.findById(id);
//    }
//
//    // Obtenir tous les programmes
//    public List<Programme> listProgrammes() {
//        return programmeRepository.findAll();
//    }
//
//    // Supprimer un programme par ID
//    public void deleteProgramme(String id) {
//        programmeRepository.deleteById(id);
//    }
//
//    // Mettre à jour un programme
//    public Programme updateProgramme(String id, Programme programmeDetails) {
//        Optional<Programme> optionalProgramme = programmeRepository.findById(id);
//        if (optionalProgramme.isPresent()) {
//            Programme programmeExistant = optionalProgramme.get();
//            programmeExistant.setNomProgramme(programmeDetails.getNomProgramme());
//            programmeExistant.setDescription(programmeDetails.getDescription());
//            programmeExistant.setTypeObjectif(programmeDetails.getTypeObjectif());
//            programmeExistant.setDateDebut(programmeDetails.getDateDebut());
//            programmeExistant.setDateFin(programmeDetails.getDateFin());
//            programmeExistant.setEstActif(programmeDetails.isEstActif());
//            return programmeRepository.save(programmeExistant);
//        }
//        return null;
//    }
}
