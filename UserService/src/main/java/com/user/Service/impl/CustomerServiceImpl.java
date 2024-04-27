package com.user.Service.impl;

import com.user.DTO.UserInfoDto;
import com.user.Entities.Customer;
import com.user.Entities.User;
import com.user.Repository.CustomerRepository;
import com.user.Repository.UserRepository;
import com.user.Service.CustomerService;
import com.user.mapper.CustomerMapper;
import com.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private UserRepository userRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, UserRepository userRepository) {
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
    }


    @Override
    public Customer add(UserInfoDto userInfoDto) {
        User user = UserMapper.mapToUser(userInfoDto);
        User user1 = userRepository.save(user);
        Customer customer= CustomerMapper.mapToCustomer(userInfoDto);
        customer.setUser(user1);
        Customer newCustomer = customerRepository.save(customer);
        return newCustomer;

    }

    @Override
    public List<Customer> get() {

        return customerRepository.findAll();
    }

    @Override
    public Customer get(Long id) {
        return customerRepository.findById(id).orElseThrow(()->new RuntimeException("Customer Not Found"));
    }
}
