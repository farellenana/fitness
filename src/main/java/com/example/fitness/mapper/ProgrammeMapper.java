package com.example.fitness.mapper;


import com.example.fitness.dto.ProgrammeDTO;
import com.example.fitness.model.Programme;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProgrammeMapper {

    @Mapping(target = "userID", expression = "java(programme.getUser() != null ? programme.getUser().getId() : null)")// Associer userID dans ProgrammeDTO
    ProgrammeDTO toDto(Programme programme);

    @Mapping(source = "userID", target = "user.id")// Associer userID à l'entité Programme
    Programme toEntity(ProgrammeDTO programmeDTO);

//    @Mapping(source = "user.id", target = "userID")// Associer userID dans ProgrammeDTO
//    ProgrammeDTO toDto(Programme programme);
//
//    @Mapping(source = "userID", target = "user.id")// Associer userID à l'entité Programme
//    Programme toEntity(ProgrammeDTO programmeDTO);
}
