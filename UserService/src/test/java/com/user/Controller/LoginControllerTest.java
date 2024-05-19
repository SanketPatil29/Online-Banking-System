package com.user.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.Controller.LoginController;
import com.user.DTO.LoginDTO;
import com.user.Repository.CustomerRepository;
import com.user.Service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@WebMvcTest(LoginController.class)
@AutoConfigureMockMvc
class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @MockBean
    private CustomerRepository customerRepository;

    @Test
    void login_ValidCredentials_ReturnsCustomerId() throws Exception {
        // Arrange
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername("testUser");
        loginDTO.setPassword("testPassword");
        loginDTO.setRole("testRole");

        when(userService.authenticateUser(anyString(), anyString(), anyString())).thenReturn(true);
        when(customerRepository.findCustomerIdByUsername(anyString())).thenReturn(1L);

        // Act & Assert using MockMvc
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("1"));
    }

    @Test
    void login_InvalidCredentials_ReturnsUnauthorized() throws Exception {
        // Arrange
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername("testUser");
        loginDTO.setPassword("testPassword");
        loginDTO.setRole("testRole");

        when(userService.authenticateUser(anyString(), anyString(), anyString())).thenReturn(false);

        // Act & Assert using MockMvc
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDTO)))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    void login_ValidCredentialsButFailedToRetrieveCustomerId_ReturnsInternalServerError() throws Exception {
        // Arrange
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername("testUser");
        loginDTO.setPassword("testPassword");
        loginDTO.setRole("testRole");

        when(userService.authenticateUser(anyString(), anyString(), anyString())).thenReturn(true);
        when(customerRepository.findCustomerIdByUsername(anyString())).thenReturn(null);

        // Act & Assert using MockMvc
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDTO)))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError());
    }

    // Add more tests for exception handling if needed
}
