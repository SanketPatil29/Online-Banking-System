package com.user.DTO;

import lombok.Data;

import java.sql.Date;
@Data
public class UserInfoDto {
    private String username;
    private String password;
    private  String role;


    private String firstname;
    private String lastname;

    private String gender;

    private Date dob;

    private String address;

    private Long mobile;

    private String email;


}
