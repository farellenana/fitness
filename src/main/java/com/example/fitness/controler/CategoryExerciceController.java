package com.example.fitness.controler;

import com.example.fitness.dto.CategoryExerciceDTO;
import com.example.fitness.service.CategoryExerciceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/category")
public class CategoryExerciceController {
    private CategoryExerciceService categoryExerciceService;

    public CategoryExerciceController(CategoryExerciceService categoryExerciceService) {
        this.categoryExerciceService = categoryExerciceService;
    }

    // Ajouter un programme
    @PostMapping
    public ResponseEntity<CategoryExerciceDTO> addcategory(@RequestBody CategoryExerciceDTO categoryExerciceDTO) {
        CategoryExerciceDTO savedCategory = categoryExerciceService.addCategoryExercice(categoryExerciceDTO);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    // Mettre à jour un programme
    @PutMapping("/{categoryID}")
    public ResponseEntity<CategoryExerciceDTO> updateCategory(
            @PathVariable Long categoryID,
            @RequestBody CategoryExerciceDTO categoryExerciceDTO) {
        CategoryExerciceDTO updatedCategory = categoryExerciceService.updateCategory(categoryID, categoryExerciceDTO);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    // Obtenir tous les programmes
    @GetMapping
    public ResponseEntity<List<CategoryExerciceDTO>> getAllProgrammes() {
        List<CategoryExerciceDTO> category = categoryExerciceService.getAllCategory();
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    // Obtenir un programme par ID
    @GetMapping("/{categoryID}")
    public ResponseEntity<CategoryExerciceDTO> getCategoryeById(@PathVariable Long categoryID) {
        CategoryExerciceDTO programme = categoryExerciceService.getCategoryById(categoryID);
        return new ResponseEntity<>(programme, HttpStatus.OK);
    }

    // Supprimer un programme
    @DeleteMapping("/{categoryID}")
    public ResponseEntity<Map<String, String>> deleteCategory(@PathVariable Long categoryID) {
        categoryExerciceService.deleteCategory(categoryID);
        Map<String, String> response = new HashMap<>();
        response.put("message", "category supprimé avec succès");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
