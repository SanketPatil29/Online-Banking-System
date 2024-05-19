package com.user.Repository;

import com.user.Entities.Customer;
import com.user.Entities.User;
import com.user.Repository.CustomerRepository;
import com.user.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void saveCustomer_ReturnsSavedCustomer() {
        // Arrange
        User user = new User("testUser", "testPassword", "testRole");
        userRepository.save(user);
        Customer customer = new Customer();
        customer.setFirstname("John");
        customer.setLastname("Doe");
        customer.setGender("Male");
        customer.setDob(Date.valueOf("1990-01-01"));
        customer.setAddress("123 Main St");
        customer.setType("Personal");
        customer.setMobile(1234567890L);
        customer.setEmail("test@example.com");
        customer.setUser(user);

        // Act
        Customer savedCustomer = customerRepository.save(customer);

        // Assert
        assertEquals(customer.getFirstname(), savedCustomer.getFirstname());
        assertEquals(customer.getLastname(), savedCustomer.getLastname());
        // Add more assertions as needed
    }

    @Test
    void findCustomerIdByUsername_ExistingUsername_ReturnsCustomerId() {
        // Arrange
        User user = new User("testUser", "testPassword", "testRole");
        userRepository.save(user);
        Customer customer = new Customer();
        customer.setFirstname("John");
        customer.setLastname("Doe");
        customer.setUser(user);
        customerRepository.save(customer);

        // Act
        Long customerId = customerRepository.findCustomerIdByUsername("testUser");

        // Assert
        assertEquals(customer.getCustomer_id(), customerId);
    }

    @Test
    void findCustomerIdByUsername_NonExistingUsername_ReturnsNull() {
        // Arrange - No need to arrange anything

        // Act
        Long customerId = customerRepository.findCustomerIdByUsername("nonExistingUser");

        // Assert
        assertEquals(null, customerId);
    }

    @Test
    void findById_ExistingId_ReturnsCustomer() {
        // Arrange
        User user = new User("testUser", "testPassword", "testRole");
        userRepository.save(user);
        Customer customer = new Customer();
        customer.setFirstname("John");
        customer.setLastname("Doe");
        customer.setUser(user);
        Customer savedCustomer = customerRepository.save(customer);

        // Act
        Optional<Customer> foundCustomer = customerRepository.findById(savedCustomer.getCustomer_id());

        // Assert
        assertTrue(foundCustomer.isPresent());
        assertEquals(savedCustomer.getCustomer_id(), foundCustomer.get().getCustomer_id());
        // Add more assertions as needed
    }

    @Test
    void findById_NonExistingId_ReturnsEmptyOptional() {
        // Arrange - No need to arrange anything

        // Act
        Optional<Customer> foundCustomer = customerRepository.findById(-1L); // Non-existing ID

        // Assert
        assertTrue(foundCustomer.isEmpty());
    }
}
