package com.user.Service;

import com.user.DTO.UserInfoDto;
import com.user.Entities.Customer;

import java.util.List;

public interface CustomerService {

    Customer add(UserInfoDto userInfoDto);

    List<Customer> get();

    Customer get(Long id);
}
