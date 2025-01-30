package com.example.fitness.mapper;

import com.example.fitness.dto.PlanEntrainementDTO;
import com.example.fitness.model.PlanEntrainement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Configuration;

@Mapper(componentModel = "spring")
public interface PlanEntrainementMapper {

    @Mapping(target = "userID", expression = "java(planEntrainement.getUser() != null ? planEntrainement.getUser().getId() : null)")
    @Mapping(target = "objectifID", expression = "java(planEntrainement.getObjectif() != null ? planEntrainement.getObjectif().getObjectifID() : null)")
    PlanEntrainementDTO toDto(PlanEntrainement planEntrainement);

    @Mapping(target = "user.id", source = "userID")
    @Mapping(target = "objectif.objectifID", source = "objectifID")
    PlanEntrainement toEntity(PlanEntrainementDTO planEntrainementDTO);

}
