package com.example.fitness.mapper;

import com.example.fitness.dto.CategoryExerciceDTO;
import com.example.fitness.model.CategoryExercice;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryExerciceMapper {
    CategoryExerciceDTO toDto (CategoryExercice categoryExercice);
    CategoryExercice toEntity(CategoryExerciceDTO categoryExerciceDTO);
}
