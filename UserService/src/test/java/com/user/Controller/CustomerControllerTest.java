package com.user.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.Controller.CustomerController;
import com.user.DTO.UserInfoDto;
import com.user.Entities.Customer;
import com.user.Service.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Date;
import java.util.Collections;

import static org.mockito.Mockito.when;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Test
    void addCustomer_ValidInput_ReturnsCreated() throws Exception {
        // Arrange
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setUsername("testUser");
        userInfoDto.setPassword("testPassword");
        userInfoDto.setRole("testRole");
        userInfoDto.setFirstname("John");
        userInfoDto.setLastname("Doe");
        userInfoDto.setType("Personal");
        userInfoDto.setGender("Male");
        userInfoDto.setDob(Date.valueOf("1990-01-01"));
        userInfoDto.setAddress("123 Main St");
        userInfoDto.setMobile(1234567890L);
        userInfoDto.setEmail("test@example.com");

        Customer addedCustomer = new Customer();
        addedCustomer.setCustomer_id(1L);
        addedCustomer.setFirstname("John");
        addedCustomer.setLastname("Doe");
        // Set other fields as needed

        when(customerService.add(userInfoDto)).thenReturn(addedCustomer);

        // Act & Assert using MockMvc
        mockMvc.perform(MockMvcRequestBuilders.post("/api/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(userInfoDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customer_id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customer_id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstname").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastname").value("Doe"));
    }

    @Test
    void getAllCustomers_ReturnsCustomers() throws Exception {
        // Arrange
        Customer customer = new Customer();
        customer.setCustomer_id(1L);
        customer.setFirstname("John");
        customer.setLastname("Doe");
        customer.setGender("Male");
        customer.setDob(Date.valueOf("1990-01-01"));
        customer.setAddress("123 Main St");
        customer.setType("Personal");
        customer.setMobile(1234567890L);
        customer.setEmail("test@example.com");


        when(customerService.getAllCustomers()).thenReturn(Collections.singletonList(customer));

        // Act & Assert using MockMvc
        mockMvc.perform(MockMvcRequestBuilders.get("/api/customer/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].customer_id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].customer_id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstname").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastname").value("Doe"));
    }

    @Test
    void getCustomerById_ValidId_ReturnsCustomer() throws Exception {
        // Arrange
        Long customerId = 1L;
        Customer customer = new Customer();
        customer.setCustomer_id(customerId);
        customer.setFirstname("John");
        customer.setLastname("Doe");
        // Set other fields as needed

        when(customerService.getCustomerById(customerId)).thenReturn(customer);

        // Act & Assert using MockMvc
        mockMvc.perform(MockMvcRequestBuilders.get("/api/customer/{c_id}", customerId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customer_id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customer_id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstname").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastname").value("Doe"));
    }

    // Helper method to convert object to JSON string
    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
