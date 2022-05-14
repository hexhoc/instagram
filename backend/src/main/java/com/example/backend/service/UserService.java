package com.example.backend.service;

import com.example.backend.dto.UserDTO;
import com.example.backend.entity.User;
import com.example.backend.payload.request.SignupRequest;

import java.security.Principal;

public interface UserService {
    User createUser(SignupRequest userIn);

    User updateUser(UserDTO userDTO, Principal principal);

    User getCurrentUser(Principal principal);

    User getUserById(Long id);

    User getUserByPrincipal(Principal principal);
}
