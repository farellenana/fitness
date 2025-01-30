package com.example.fitness.mapper;

import com.example.fitness.dto.ExerciceDTO;
import com.example.fitness.dto.SeanceEntrainementDTO;
import com.example.fitness.model.Exercice;
import com.example.fitness.model.SeanceEntrainement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SeanceEntrainementMapper {

    @Mapping(target = "userID", expression = "java(seanceEntrainement.getUser() != null ? seanceEntrainement.getUser().getId() : null)")
    @Mapping(target = "planID", expression = "java(seanceEntrainement.getPlanEntrainement() != null ? seanceEntrainement.getPlanEntrainement().getPlanID() : null)")
    @Mapping(target = "exercices", expression = "java(seanceEntrainement.getExercices() != null ? toExerciceDTOList(seanceEntrainement.getExercices()) : null)")
    SeanceEntrainementDTO toDto(SeanceEntrainement seanceEntrainement);

    @Mapping(target = "user.id", source = "userID")
    @Mapping(target = "planEntrainement.planID", source = "planID")
    @Mapping(target = "exercices", expression = "java(toExerciceEntityList(seanceEntrainementDTO.getExercices()))")
    SeanceEntrainement toEntity(SeanceEntrainementDTO seanceEntrainementDTO);

    // Helper methods for List<ExerciceDTO> and List<Exercice>
    default List<ExerciceDTO> toExerciceDTOList(List<Exercice> exercices) {
        return exercices.stream().map(this::toExerciceDTO).toList();
    }

    default List<Exercice> toExerciceEntityList(List<ExerciceDTO> exerciceDTOs) {
        return exerciceDTOs.stream().map(this::toExerciceEntity).toList();
    }

    ExerciceDTO toExerciceDTO(Exercice exercice);

    Exercice toExerciceEntity(ExerciceDTO exerciceDTO);
}
