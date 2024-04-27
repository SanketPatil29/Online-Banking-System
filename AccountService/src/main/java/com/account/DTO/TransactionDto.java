package com.account.DTO;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
public class TransactionDto {
    private Long transactionId;
    private String txType;
    private BigDecimal amount;
    private String description;
    private LocalDateTime transactionDateTime;
    private String status;
    private Long accountId;
}
