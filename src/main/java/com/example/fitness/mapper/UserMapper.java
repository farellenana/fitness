package com.example.fitness.mapper;

import com.example.fitness.dto.UserDTO;
import com.example.fitness.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    // Mapper UserDTO to User
    @Mapping(source = "fullName", target = "firstName", qualifiedByName = "extractFirstNameFromFullName")
    @Mapping(source = "fullName", target = "lastName", qualifiedByName = "extractLastNameFromFullName")
    User toUser(UserDTO userDTO);

    // Mapper User to UserDTO
    @Mapping(target = "fullName", expression = "java(user.getFirstName() + \" \" + user.getLastName())")
    UserDTO toUserDTO(User user);

    // Méthode pour extraire le prénom depuis le fullName
    @Named("extractFirstNameFromFullName")
    default String extractFirstNameFromFullName(String fullName) {
        return fullName != null ? fullName.split(" ")[0] : "";
    }

    // Méthode pour extraire le nom depuis le fullName
    @Named("extractLastNameFromFullName")
    default String extractLastNameFromFullName(String fullName) {
        return fullName != null && fullName.split(" ").length > 1 ? fullName.split(" ")[1] : "";
    }
}
