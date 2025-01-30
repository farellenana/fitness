package com.example.fitness.controler;


import com.example.fitness.dto.ExerciceDTO;
import com.example.fitness.service.ExerciceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/exercices")
public class ExerciceController {
    private final ExerciceService exerciceService;

    @Autowired
    public ExerciceController(ExerciceService exerciceService) {
        this.exerciceService = exerciceService;
    }

    @PostMapping
    public ResponseEntity<ExerciceDTO> addExercice(@RequestBody ExerciceDTO exerciceDTO) {
        return new ResponseEntity<>(exerciceService.addExercice(exerciceDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{exerciceID}")
    public ResponseEntity<ExerciceDTO> updateExercice(
            @PathVariable Long exerciceID,
            @RequestBody ExerciceDTO exerciceDTO) {
        return new ResponseEntity<>(exerciceService.updateExercice(exerciceID, exerciceDTO), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ExerciceDTO>> getAllExercices() {
        return new ResponseEntity<>(exerciceService.getAllExercices(), HttpStatus.OK);
    }

    @GetMapping("/{exerciceID}")
    public ResponseEntity<ExerciceDTO> getExerciceById(@PathVariable Long exerciceID) {
        return new ResponseEntity<>(exerciceService.getExerciceById(exerciceID), HttpStatus.OK);
    }

    @DeleteMapping("/{exerciceID}")
    public ResponseEntity<Map<String, String>> deleteExercice(@PathVariable Long exerciceID) {
        exerciceService.deleteExercice(exerciceID);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Exercice supprimé avec succès");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
