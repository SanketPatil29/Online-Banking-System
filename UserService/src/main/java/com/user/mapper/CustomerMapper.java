package com.user.mapper;

import com.user.DTO.UserInfoDto;
import com.user.Entities.Customer;

public class CustomerMapper {
    public static Customer mapToCustomer(UserInfoDto userInfoDto) {
        Customer customer = new Customer();

        // Map the fields from UserInfoDto to Customer
        customer.setFirstname(userInfoDto.getFirstname());
        customer.setLastname(userInfoDto.getLastname());
        customer.setGender(userInfoDto.getGender());
        customer.setType(userInfoDto.getType());
        customer.setDob(userInfoDto.getDob());
        customer.setAddress(userInfoDto.getAddress());
        customer.setMobile(userInfoDto.getMobile());
        customer.setEmail(userInfoDto.getEmail());

        return customer;
    }
}
