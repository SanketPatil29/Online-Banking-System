package com.user.Service.impl;

import com.user.DTO.UserInfoDto;
import com.user.Entities.Account;
import com.user.Entities.Customer;
import com.user.Entities.User;
import com.user.Repository.CustomerRepository;
import com.user.Repository.UserRepository;
import com.user.Service.AccountClient;
import com.user.mapper.CustomerMapper;
import com.user.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AccountClient accountClient;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void add_ValidInput_ReturnsCustomer() {
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

        User user = UserMapper.mapToUser(userInfoDto);
        when(userRepository.save(any(User.class))).thenReturn(user);

        when(customerRepository.save(any(Customer.class))).thenAnswer(invocation -> {
            Customer savedCustomer = invocation.getArgument(0);
            savedCustomer.setCustomer_id(1L); // Set a dummy customer ID
            return savedCustomer;
        });

        Account mockAccount = new Account();
        mockAccount.setAccount_id(1L);
        when(accountClient.createAccount(any(Account.class))).thenReturn(mockAccount);

        // Act
        Customer result = customerService.add(userInfoDto);

        // Assert
        assertNotNull(result);
        assertEquals("John", result.getFirstname());
        assertEquals("Doe", result.getLastname());
        assertEquals("Personal", result.getType());
    }

    @Test
    void getAllCustomers_ValidInput_ReturnsCustomersWithAccounts() {
        // Arrange
        Customer customer = new Customer();
        customer.setCustomer_id(1L);
        when(customerRepository.findAll()).thenReturn(Collections.singletonList(customer));

        Account mockAccount = new Account();
        mockAccount.setAccount_id(1L);
        when(accountClient.getAccountsOfClient(anyLong())).thenReturn(Collections.singletonList(mockAccount));

        // Act
        List<Customer> customers = customerService.getAllCustomers();

        // Assert
        assertFalse(customers.isEmpty());
        assertEquals(1, customers.size());
        assertNotNull(customers.get(0).getAccounts());
        assertEquals(1, customers.get(0).getAccounts().size());
        // Add more assertions as needed
    }

    @Test
    void getCustomerById_ValidId_ReturnsCustomerWithAccount() {
        // Arrange
        Long customerId = 1L;
        Customer customer = new Customer();
        customer.setCustomer_id(customerId);
        when(customerRepository.findById(customerId)).thenReturn(java.util.Optional.of(customer));

        Account mockAccount = new Account();
        mockAccount.setAccount_id(1L);
        when(accountClient.getAccountsOfClient(customerId)).thenReturn(Collections.singletonList(mockAccount));

        // Act
        Customer result = customerService.getCustomerById(customerId);

        // Assert
        assertNotNull(result);
        assertEquals(customerId, result.getCustomer_id());
        assertNotNull(result.getAccounts());
        assertEquals(1, result.getAccounts().size());
        // Add more assertions as needed
    }
}
