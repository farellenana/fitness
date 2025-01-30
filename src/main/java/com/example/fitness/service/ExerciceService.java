package com.example.fitness.service;


import com.example.fitness.dto.ExerciceDTO;

import com.example.fitness.dto.ProgrammeDTO;
import com.example.fitness.mapper.ExerciceMapper;
import com.example.fitness.model.Exercice;
import com.example.fitness.model.Programme;
import com.example.fitness.repository.ExerciceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciceService {
    private ExerciceRepository exerciceRepository;
    private ExerciceMapper exerciceMapper;

    public ExerciceService(ExerciceRepository exerciceRepository, ExerciceMapper exerciceMapper) {
        this.exerciceRepository = exerciceRepository;
        this.exerciceMapper = exerciceMapper;
    }

    // Ajouter un exercice
    public ExerciceDTO addExercice(ExerciceDTO exerciceDTO) {
        Exercice exercice = exerciceMapper.toEntity(exerciceDTO);
        Exercice savedexercice = exerciceRepository.save(exercice);
        return exerciceMapper.toDto(savedexercice);
    }

    // Mettre à jour un exercice
    public ExerciceDTO updateExercice(Long exerciceID, ExerciceDTO exerciceDTO){
        Optional <Exercice> existingExercice= exerciceRepository.findById(exerciceID);
        if (existingExercice.isPresent()) {

            Exercice exercice= existingExercice.get();
            exercice.setNomExercice(exerciceDTO.getNomExercice());
            exercice.setDescription(exerciceDTO.getDescription());
            exercice.setUrlVideo(exerciceDTO.getUrlVideo());
            exercice.setUrlImage(exerciceDTO.getUrlImage());

            Exercice updaedExercice= exerciceRepository.save(exercice);
            return exerciceMapper.toDto(updaedExercice);
        }
        throw new IllegalArgumentException("Exercice not found with ID: " + exerciceID);
    }

    // Obtenir tous les exercices
     public List<ExerciceDTO> getAllExercices(){
         List<Exercice> exercices= exerciceRepository.findAll();
         return  exercices.stream().map(exerciceMapper::toDto)
                 .toList();
    }


    // Obtenir un exercice par ID
    public ExerciceDTO getExerciceById(Long exerciceID){
        Optional<Exercice> exercice= exerciceRepository.findById(exerciceID);
        if (exercice.isPresent()) {
            return exerciceMapper.toDto(exercice.get());
        }
        throw new IllegalArgumentException("Eexercice not found with ID: " + exerciceID);
    }

    // Supprimer un exercice
    public void deleteExercice(Long exerciceID){
        if(exerciceRepository.existsById(exerciceID)){
            exerciceRepository.deleteById(exerciceID);
        }else {
            throw new IllegalArgumentException("exercice not found with ID: " + exerciceID);
        }
    }

}
