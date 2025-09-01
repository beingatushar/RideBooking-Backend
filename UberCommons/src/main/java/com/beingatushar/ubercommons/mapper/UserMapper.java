package com.beingatushar.ubercommons.mapper;

import com.beingatushar.ubercommons.dto.UserDTO;
import com.beingatushar.ubercommons.entity.user.User;

public class UserMapper {

    public static UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }

    public static User toEntity(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword()); // Remember to hash this in the service layer
        return user;
    }
}