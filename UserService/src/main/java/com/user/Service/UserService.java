package com.user.Service;

import org.springframework.stereotype.Service;

public interface UserService {
    boolean authenticateUser(String username, String password, String role);

}
