package com.example.fitness.controler;

import com.example.fitness.dto.ObjectifsDTO;
import com.example.fitness.service.ObjectifsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("http://localhost:8080")
@RestController
@RequestMapping("/objectifs")
public class ObjectifsController {
    private final ObjectifsService objectifsService;

    @Autowired
    public ObjectifsController(ObjectifsService objectifsService) {
        this.objectifsService = objectifsService;
    }

    // Ajouter un objectif
    @PostMapping
    public ResponseEntity<ObjectifsDTO> addObjectif(@RequestBody ObjectifsDTO objectifsDTO) {
        ObjectifsDTO savedObjectif = objectifsService.addObjectif(objectifsDTO);
        return new ResponseEntity<>(savedObjectif, HttpStatus.CREATED);
    }

    // Mettre à jour un objectif
    @PutMapping("/{objectifID}")
    public ResponseEntity<ObjectifsDTO> updateObjectif(
            @PathVariable Long objectifID,
            @RequestBody ObjectifsDTO objectifsDTO) {
        ObjectifsDTO updatedObjectif = objectifsService.updateObjectif(objectifID, objectifsDTO);
        return new ResponseEntity<>(updatedObjectif, HttpStatus.OK);
    }

    // Obtenir tous les objectifs
    @GetMapping
    public ResponseEntity<List<ObjectifsDTO>> getAllObjectifs() {
        List<ObjectifsDTO> objectifs = objectifsService.getAllObjectifs();
        return new ResponseEntity<>(objectifs, HttpStatus.OK);
    }

    // Obtenir un objectif par ID
    @GetMapping("/{objectifID}")
    public ResponseEntity<ObjectifsDTO> getObjectifById(@PathVariable Long objectifID) {
        ObjectifsDTO objectif = objectifsService.getObjectifById(objectifID);
        return new ResponseEntity<>(objectif, HttpStatus.OK);
    }

    // Supprimer un objectif
    @DeleteMapping("/{objectifID}")
    public ResponseEntity<Map<String, String>> deleteObjectif(@PathVariable Long objectifID) {
        objectifsService.deleteObjectif(objectifID);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Objectif supprimé avec succès");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Obtenir les objectifs d'un utilisateur spécifique
    @GetMapping("/user/{userID}")
    public ResponseEntity<List<ObjectifsDTO>> getObjectifsByUser(@PathVariable Long userID) {
        List<ObjectifsDTO> objectifs = objectifsService.getObjectifsByUser(userID);
        return new ResponseEntity<>(objectifs, HttpStatus.OK);
    }
}
