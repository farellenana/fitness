package com.example.fitness.service;

import com.example.fitness.dto.CategoryExerciceDTO;
import com.example.fitness.dto.ExerciceDTO;
import com.example.fitness.mapper.CategoryExerciceMapper;
import com.example.fitness.model.CategoryExercice;
import com.example.fitness.model.Exercice;
import com.example.fitness.repository.CategoryExerciceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryExerciceService {
    private CategoryExerciceRepository categoryExerciceRepository;
    private CategoryExerciceMapper categoryExerciceMapper;

    public CategoryExerciceService(CategoryExerciceRepository categoryExerciceRepository, CategoryExerciceMapper categoryExerciceMapper) {
        this.categoryExerciceRepository = categoryExerciceRepository;
        this.categoryExerciceMapper = categoryExerciceMapper;
    }

    public CategoryExerciceDTO addCategoryExercice(CategoryExerciceDTO categoryExerciceDTO) {
        CategoryExercice categoryExercice = categoryExerciceMapper.toEntity(categoryExerciceDTO);
        CategoryExercice savedcategory = categoryExerciceRepository.save(categoryExercice);
        return categoryExerciceMapper.toDto(savedcategory);
    }

    public CategoryExerciceDTO getCategoryById(Long categoryID){
        Optional<CategoryExercice> categoryCategory= categoryExerciceRepository.findById(categoryID);
        if (categoryCategory.isPresent()) {
            return categoryExerciceMapper.toDto(categoryCategory.get());
        }
        throw new IllegalArgumentException("category not found with ID: " + categoryID);
    }

    public List<CategoryExerciceDTO> getAllCategory(){
        List<CategoryExercice> categoryExercice= categoryExerciceRepository.findAll();
        return  categoryExercice.stream().map(categoryExerciceMapper::toDto)
                .toList();
    }

    public void deleteCategory(Long categoryID){
        if(categoryExerciceRepository.existsById(categoryID)){
            categoryExerciceRepository.deleteById(categoryID);
        }else {
            throw new IllegalArgumentException("exercice not found with ID: " + categoryID);
        }
    }

    public CategoryExerciceDTO updateCategory(Long categoryID, CategoryExerciceDTO categoryExerciceDTO){
        Optional <CategoryExercice> existingCategory= categoryExerciceRepository.findById(categoryID);
        if (existingCategory.isPresent()) {

            CategoryExercice categoryExercice= existingCategory.get();
            categoryExercice.setNom(categoryExerciceDTO.getNom());
            CategoryExercice updatedCategory= categoryExerciceRepository.save(categoryExercice);
            return categoryExerciceMapper.toDto(updatedCategory);
        }
        throw new IllegalArgumentException("Exercice not found with ID: " + categoryID);
    }

}
