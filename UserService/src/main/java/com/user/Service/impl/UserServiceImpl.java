package com.user.Service.impl;

import com.user.Entities.User;
import com.user.Repository.UserRepository;
import com.user.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean authenticateUser(String username, String password, String role) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password) && user.getRole().equals(role)) {
            return true; // Authentication successful
        }
        return false; // Authentication failed
    }
}
