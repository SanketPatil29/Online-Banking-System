package com.user.Service.impl;

import com.user.DTO.UserInfoDto;
import com.user.Entities.Account;
import com.user.Entities.Customer;
import com.user.Entities.User;
import com.user.Repository.CustomerRepository;
import com.user.Repository.UserRepository;
import com.user.Service.AccountClient;
import com.user.Service.CustomerService;
import com.user.mapper.CustomerMapper;
import com.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.gson.GsonProperties;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    private AccountClient accountClient;
    private UserRepository userRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, AccountClient accountClient, UserRepository userRepository) {
        this.customerRepository = customerRepository;
        this.accountClient = accountClient;
        this.userRepository = userRepository;
    }

//    @Override
//    public Customer add(UserInfoDto userInfoDto) {
//        User user = UserMapper.mapToUser(userInfoDto);
//        User user1 = userRepository.save(user);
//        Customer customer= CustomerMapper.mapToCustomer(userInfoDto);
//        customer.setUser(user1);
//        Customer newCustomer = customerRepository.save(customer);
//        return newCustomer;
//
//    }
@Override
public Customer add(UserInfoDto userInfoDto) {
    User user = UserMapper.mapToUser(userInfoDto);
    User savedUser = userRepository.save(user);

    Customer customer = CustomerMapper.mapToCustomer(userInfoDto);
    customer.setUser(savedUser);
    Customer savedCustomer = customerRepository.save(customer);
    System.out.println("savedCustomer "+ savedCustomer);

    // Create an account for the newly added customer
    Account account = new Account();
    account.setCustomerId(savedCustomer.getCustomer_id());
    account.setType(savedCustomer.getType()); // Set default account type or adjust as needed

    System.out.println(" before newAccount ");
    Account newAccount = accountClient.createAccount(account);
    System.out.println("newAccount  "+newAccount);

    return savedCustomer;
}


    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        List<Customer> newCustomerList = customers.stream().map(customer -> {
            if (accountClient != null) { // Check for null
                customer.setAccounts(accountClient.getAccountsOfClient(customer.getCustomer_id()));
            }
            return customer;
        }).collect(Collectors.toList());
        return newCustomerList;
    }

    @Override
    public Customer getCustomerById(Long id) {
        Customer customer =customerRepository.findById(id).orElseThrow(()->new RuntimeException("Customer Not Found"));
        customer.setAccounts(accountClient.getAccountsOfClient(customer.getCustomer_id()));
        return customer;
    }



}
