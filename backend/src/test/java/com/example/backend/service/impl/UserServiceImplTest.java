package com.example.backend.service.impl;

import com.example.backend.dto.UserDTO;
import com.example.backend.entity.User;
import com.example.backend.payload.request.SignupRequest;
import com.example.backend.service.UserService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.security.Principal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("integrationtest")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    @Order(1)
    void createUser() {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setFirstname("Firstname");
        signupRequest.setLastname("Lastname");
        signupRequest.setEmail("email@email.ru");
        signupRequest.setUsername("Username");
        signupRequest.setPassword("password");
        signupRequest.setConfirmPassword("password");

        User user = userService.createUser(signupRequest);
        assertEquals(user.getUsername(), signupRequest.getUsername());
    }

    @Order(2)
    @Test
    void updateUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setFirstname("NewFirstname");
        userDTO.setLastname("NewLastname");
        userDTO.setBio("Bio");

        Principal principal = new Principal(){
            @Override
            public String getName() {
                return "Username";
            }
        };

        User user = userService.updateUser(userDTO, principal);

        assertNotNull(user);
        assertEquals(userDTO.getBio(), user.getBio());
    }

    @Test
    @Order(3)
    void getCurrentUser() {
        Principal principal = new Principal(){
            @Override
            public String getName() {
                return "Username";
            }
        };

        User user = userService.getCurrentUser(principal);
        assertNotNull(user);
    }

    @Test
    void getUserById() {
        User user = userService.getUserById(1L);
        assertNotNull(user);
    }
}