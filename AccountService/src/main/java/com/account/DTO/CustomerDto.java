package com.account.DTO;

import lombok.Data;

import java.sql.Date;
@Data
public class CustomerDto {
    private String username;
    private String password;
    private  String role;


    private String firstname;
    private String lastname;
    private String type;
    private String gender;

    private Date dob;

    private String address;

    private Long mobile;

    private String email;
}


