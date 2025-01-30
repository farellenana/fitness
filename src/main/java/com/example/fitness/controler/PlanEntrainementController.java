package com.example.fitness.controler;

import com.example.fitness.dto.PlanEntrainementDTO;
import com.example.fitness.service.PlanEntrainementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/plan")
public class PlanEntrainementController {

    private final PlanEntrainementService planEntrainementService;

    @Autowired
    public PlanEntrainementController(PlanEntrainementService planEntrainementService) {
        this.planEntrainementService = planEntrainementService;
    }

    // Ajouter un Plan d'Entrainement
    @PostMapping
    public ResponseEntity<PlanEntrainementDTO> addPlan(@RequestBody PlanEntrainementDTO planEntrainementDTO) {
        PlanEntrainementDTO savedPlan = planEntrainementService.addPlanEntrainement(planEntrainementDTO);
        return new ResponseEntity<>(savedPlan, HttpStatus.CREATED);
    }

    // Obtenir tous les Plans d'Entrainement
    @GetMapping
    public ResponseEntity<List<PlanEntrainementDTO>> getAllPlans() {
        List<PlanEntrainementDTO> plans = planEntrainementService.getAllPlans();
        return new ResponseEntity<>(plans, HttpStatus.OK);
    }

    // Obtenir un Plan d'Entrainement par ID
    @GetMapping("/{planID}")
    public ResponseEntity<PlanEntrainementDTO> getPlanById(@PathVariable Long planID) {
        PlanEntrainementDTO plan = planEntrainementService.getPlanById(planID);
        return new ResponseEntity<>(plan, HttpStatus.OK);
    }

    // Mettre à jour un Plan d'Entrainement
    @PutMapping("/{planID}")
    public ResponseEntity<PlanEntrainementDTO> updatePlan(@PathVariable Long planID,
                                                          @RequestBody PlanEntrainementDTO planEntrainementDTO) {
        PlanEntrainementDTO updatedPlan = planEntrainementService.updatePlanEntrainement(planID, planEntrainementDTO);
        return new ResponseEntity<>(updatedPlan, HttpStatus.OK);
    }

    // Supprimer un Plan d'Entrainement
    @DeleteMapping("/{planID}")
    public ResponseEntity<Map<String, String>> deletePlan(@PathVariable Long planID) {
        planEntrainementService.deletePlan(planID);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Plan d'entraînement supprimé avec succès");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
