package com.example.fitness.mapper;

import com.example.fitness.dto.ExerciceDTO;
import com.example.fitness.model.CategoryExercice;
import com.example.fitness.model.Exercice;
import com.example.fitness.model.SeanceEntrainement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ExerciceMapper {



    @Mapping(target = "categoryExID", expression = "java(exercice.getCategoryExercice() != null ? exercice.getCategoryExercice().getCategoryID() : null)")
    @Mapping(target = "seanceID", expression = "java(exercice.getSeanceEntrainement() != null ? exercice.getSeanceEntrainement().getSeanceID() : null)")
    ExerciceDTO toDto (Exercice exercice);


    @Mapping(source = "categoryExID", target = "categoryExercice.categoryID")
    @Mapping(source = "seanceID", target = "seanceEntrainement.seanceID")
    Exercice toEntity(ExerciceDTO exerciceDTO);

}
