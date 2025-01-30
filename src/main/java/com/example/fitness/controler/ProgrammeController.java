package com.example.fitness.controler;

import com.example.fitness.dto.ProgrammeDTO;
import com.example.fitness.service.ProgrammeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@CrossOrigin("http://localhost:4200")
@CrossOrigin("http://localhost:8080")
@RestController
@RequestMapping("/programmes")
public class ProgrammeController {


    private final ProgrammeService programmeService;

    @Autowired
    public ProgrammeController(ProgrammeService programmeService) {
        this.programmeService = programmeService;
    }

    // Ajouter un programme
    @PostMapping
    public ResponseEntity<ProgrammeDTO> addProgramme(@RequestBody ProgrammeDTO programmeDTO) {
        ProgrammeDTO savedProgramme = programmeService.addProgramme(programmeDTO);
        return new ResponseEntity<>(savedProgramme, HttpStatus.CREATED);
    }

    // Mettre à jour un programme
    @PutMapping("/{programmeID}")
    public ResponseEntity<ProgrammeDTO> updateProgramme(
            @PathVariable Long programmeID,
            @RequestBody ProgrammeDTO programmeDTO) {
        ProgrammeDTO updatedProgramme = programmeService.updateProgramme(programmeID, programmeDTO);
        return new ResponseEntity<>(updatedProgramme, HttpStatus.OK);
    }

    // Obtenir tous les programmes
    @GetMapping
    public ResponseEntity<List<ProgrammeDTO>> getAllProgrammes() {
        List<ProgrammeDTO> programmes = programmeService.getAllProgrammes();
        return new ResponseEntity<>(programmes, HttpStatus.OK);
    }

    // Obtenir un programme par ID
    @GetMapping("/{programmeID}")
    public ResponseEntity<ProgrammeDTO> getProgrammeById(@PathVariable Long programmeID) {
        ProgrammeDTO programme = programmeService.getProgrammeById(programmeID);
        return new ResponseEntity<>(programme, HttpStatus.OK);
    }

    // Supprimer un programme
    @DeleteMapping("/{programmeID}")
    public ResponseEntity<Map<String, String>> deleteProgramme(@PathVariable Long programmeID) {
        programmeService.deleteProgramme(programmeID);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Programme supprimé avec succès");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
