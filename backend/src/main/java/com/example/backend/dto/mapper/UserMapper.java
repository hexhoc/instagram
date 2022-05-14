package com.example.backend.dto.mapper;

import com.example.backend.dto.UserDTO;
import com.example.backend.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "name", target = "firstname")
    User userDTOToUser(UserDTO userDTO);

    @Mapping(source = "firstname", target = "name")
    UserDTO userToUserDTO(User user);
}


