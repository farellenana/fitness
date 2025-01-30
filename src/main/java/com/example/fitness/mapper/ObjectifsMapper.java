package com.example.fitness.mapper;

import com.example.fitness.dto.ObjectifsDTO;
import com.example.fitness.model.Objectifs;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ObjectifsMapper {
    @Mapping(target = "userID", expression = "java(objectifs.getUser() != null ? objectifs.getUser().getId() : null)")
    ObjectifsDTO toDto(Objectifs objectifs);

    @Mapping(source = "userID", target = "user.id")
    Objectifs toEntity(ObjectifsDTO objectifsDTO);

}
