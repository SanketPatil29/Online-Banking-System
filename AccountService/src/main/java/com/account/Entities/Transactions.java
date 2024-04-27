package com.account.Entities;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "transactions")
@Entity
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transaction_id;
    private String tx_type;

    private BigDecimal amount;

    private String description;

    private LocalDateTime transactionDateTime; // Date and time of the transaction

    private String status; // Status of the transaction (e.g., completed, pending)

    @JsonIgnoreProperties("transactions") // Add this annotation to prevent infinite loop
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;


}
