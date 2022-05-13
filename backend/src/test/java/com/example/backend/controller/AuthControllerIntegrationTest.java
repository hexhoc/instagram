package com.example.backend.controller;

import com.example.backend.payload.request.LoginRequest;
import com.example.backend.payload.request.SignupRequest;
import com.example.backend.payload.response.JWTTokenSuccessResponse;
import com.example.backend.security.JWTAuthenticationEntryPoint;
import com.example.backend.security.JWTTokenProvider;
import com.example.backend.service.CustomUserDetailsService;
import com.example.backend.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// @Extend no longer need, because it's included as a meta annotation in the Spring Boot test annotations
// like @DataJpaTest, @WebMvcTest, and @SpringBootTest.
@WebMvcTest(controllers = AuthController.class) //only load context(beans) for AuthController.class
@ActiveProfiles("integrationtest")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class AuthControllerIntegrationTest {
    @MockBean
    private JWTTokenProvider jwtTokenProvider;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @MockBean
    private UserService userService;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Order(1)
    void whenValidSignup_thenReturns200() throws Exception {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setFirstname("Firstname");
        signupRequest.setLastname("Lastname");
        signupRequest.setEmail("email@email.ru");
        signupRequest.setUsername("Username");
        signupRequest.setPassword("password");
        signupRequest.setConfirmPassword("password");

        mockMvc.perform(post("/api/v1/auth/signup")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(signupRequest)))
                .andExpect(status().isOk());

    }

    @Test
    @Order(2)
    void whenInvalidSignup_thenReturns400() throws Exception {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setFirstname("Firstname");
        signupRequest.setLastname("Lastname");
        signupRequest.setEmail("email@email.ru");
        signupRequest.setUsername("Username");
        signupRequest.setPassword("password");
        signupRequest.setConfirmPassword("pass"); // different password

        mockMvc.perform(post("/api/v1/auth/signup")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(signupRequest)))
                .andExpect(status().isBadRequest());

    }

    @Test
    @Order(3)
    void whenValidSignin_thenReturnsResource() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("email@email.ru");
        loginRequest.setPassword("password");

        MvcResult mvcResult = mockMvc.perform(post("/api/v1/auth/signin")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andReturn();

        String json = mvcResult.getResponse().getContentAsString();
        JWTTokenSuccessResponse jwtTokenSuccessResponse = objectMapper.readValue(json, JWTTokenSuccessResponse.class);

        Assertions.assertTrue(jwtTokenSuccessResponse.isSuccess());

    }
}
