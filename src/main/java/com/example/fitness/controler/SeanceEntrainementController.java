package com.example.fitness.controler;

import com.example.fitness.dto.SeanceEntrainementDTO;
import com.example.fitness.service.SeanceEntrainementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/seance")
public class SeanceEntrainementController {

    private final SeanceEntrainementService seanceEntrainementService;

    @Autowired
    public SeanceEntrainementController(SeanceEntrainementService seanceEntrainementService) {
        this.seanceEntrainementService = seanceEntrainementService;
    }

    // Ajouter une Séance d'Entrainement
    @PostMapping
    public ResponseEntity<SeanceEntrainementDTO> addSeance(@RequestBody SeanceEntrainementDTO seanceEntrainementDTO) {
        SeanceEntrainementDTO savedSeance = seanceEntrainementService.addSeanceEntrainement(seanceEntrainementDTO);
        return new ResponseEntity<>(savedSeance, HttpStatus.CREATED);
    }

    // Obtenir toutes les Séances d'Entrainement
    @GetMapping
    public ResponseEntity<List<SeanceEntrainementDTO>> getAllSeances() {
        List<SeanceEntrainementDTO> seances = seanceEntrainementService.getAllSeances();
        return new ResponseEntity<>(seances, HttpStatus.OK);
    }

    // Obtenir une Séance d'Entrainement par ID
    @GetMapping("/{seanceID}")
    public ResponseEntity<SeanceEntrainementDTO> getSeanceById(@PathVariable Long seanceID) {
        SeanceEntrainementDTO seance = seanceEntrainementService.getSeanceById(seanceID);
        return new ResponseEntity<>(seance, HttpStatus.OK);
    }

    // Mettre à jour une Séance d'Entrainement
    @PutMapping("/{seanceID}")
    public ResponseEntity<SeanceEntrainementDTO> updateSeance(@PathVariable Long seanceID,
                                                              @RequestBody SeanceEntrainementDTO seanceEntrainementDTO) {
        SeanceEntrainementDTO updatedSeance = seanceEntrainementService.updateSeanceEntrainement(seanceID, seanceEntrainementDTO);
        return new ResponseEntity<>(updatedSeance, HttpStatus.OK);
    }

    // Supprimer une Séance d'Entrainement
    @DeleteMapping("/{seanceID}")
    public ResponseEntity<Map<String, String>> deleteSeance(@PathVariable Long seanceID) {
        seanceEntrainementService.deleteSeance(seanceID);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Séance d'entraînement supprimée avec succès");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
