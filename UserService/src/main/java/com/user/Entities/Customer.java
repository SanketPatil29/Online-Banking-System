package com.user.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "customer")
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customer_id;
    private String firstname;
    private String lastname;

    private String gender;

    private Date dob;

    private String address;
    private String type;
    private Long mobile;

    private String email;

    @OneToOne
    @JoinColumn(name = "username")
    private User user;

    transient private List<Account> accounts;

}
