package com.user.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;


import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter


public class Account {

    private Long account_id;
    private Long customerId;

    private String type;
    private String status;
    private Date dateOpened;

    @Column(precision = 10, scale = 2) // Define precision and scale for the BigDecimal
    private BigDecimal balance;

    private List<Transactions> transactions;

}
