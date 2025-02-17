package com.example.fitness.controler;


import com.example.fitness.dto.ExerciceDTO;
import com.example.fitness.mapper.ExerciceMapper;
import com.example.fitness.model.CategoryExercice;
import com.example.fitness.model.Exercice;
import com.example.fitness.model.SeanceEntrainement;
import com.example.fitness.repository.CategoryExerciceRepository;
import com.example.fitness.repository.ExerciceRepository;
import com.example.fitness.repository.SeanceEntrainementRepository;
import com.example.fitness.service.ExerciceService;
import com.example.fitness.service.FileStorageService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/exercices")
public class    ExerciceController {
    @Autowired
    private final ExerciceService exerciceService;
    private final ExerciceMapper exerciceMapper;
    private final FileStorageService fileStorageService;
    private final ExerciceRepository exerciceRepository;
    private final CategoryExerciceRepository categoryExerciceRepository;
    private final SeanceEntrainementRepository seanceEntrainementRepository;
//    @PostMapping
//    public ResponseEntity<ExerciceDTO> addExercice(@RequestBody ExerciceDTO exerciceDTO) {
//        return new ResponseEntity<>(exerciceService.addExercice(exerciceDTO), HttpStatus.CREATED);
//    }


    public ExerciceController(ExerciceService exerciceService, ExerciceMapper exerciceMapper, FileStorageService fileStorageService, ExerciceRepository exerciceRepository, CategoryExerciceRepository categoryExerciceRepository ,SeanceEntrainementRepository seanceEntrainementRepository) {
        this.exerciceService = exerciceService;
        this.exerciceMapper = exerciceMapper;
        this.fileStorageService = fileStorageService;
        this.exerciceRepository = exerciceRepository;
        this.categoryExerciceRepository = categoryExerciceRepository;
        this.seanceEntrainementRepository=seanceEntrainementRepository;
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<?> createExercice(
            @ModelAttribute ExerciceDTO exerciceDTO,
            @RequestParam(value = "categoryExID", required = true) Long categoryExID, // 💡 Ajouter explicitement
            @RequestParam(value = "seanceID", required = false) Long seanceID, // Optionnel
            @RequestParam(value = "image", required = false) MultipartFile image,
            @RequestParam(value = "video", required = false) MultipartFile video) {

        System.out.println("🟢 DEBUG - exerciceDTO : " + exerciceDTO);
        System.out.println("🟢 DEBUG - categoryExID reçu : " + categoryExID);

        try {
            // Vérifier si la catégorie existe
            CategoryExercice category = categoryExerciceRepository.findById(categoryExID)
                    .orElseThrow(() -> new RuntimeException("Catégorie non trouvée avec l'ID : " + categoryExID));

            Exercice exercice = exerciceMapper.toEntity(exerciceDTO);
            exercice.setCategoryExercice(category);

            // Si une séance est fournie, l'ajouter
            if (seanceID != null) {
                SeanceEntrainement seance = seanceEntrainementRepository.findById(seanceID)
                        .orElseThrow(() -> new RuntimeException("Séance non trouvée avec l'ID : " + seanceID));
                exercice.setSeanceEntrainement(seance);
            }

            // Stockage des fichiers
            if (image != null && !image.isEmpty()) {
                System.out.println("🟢 DEBUG - Stockage image en cours...");
                String imageName = fileStorageService.storeFile(image, "images");
                System.out.println("🔵 Image enregistrée : " + imageName);
                exercice.setUrlImage(imageName);
            }

            if (video != null && !video.isEmpty()) {
                System.out.println("🟢 DEBUG - Stockage vidéo en cours...");
                String videoName = fileStorageService.storeFile(video, "videos");
                System.out.println("✅ Vidéo enregistrée : " + videoName);
                exercice.setUrlVideo(videoName);
            }

            Exercice savedExercice = exerciceRepository.save(exercice);
            return ResponseEntity.ok(exerciceMapper.toDto(savedExercice));

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erreur : " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'ajout de l'exercice.");
        }
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
