package com.account.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class AccountDto {
    private Long accountId; // Nullable for GET requests
    private Long customerId;
    private String type;
    private String status;
    private Date dateOpened;
    private BigDecimal balance;
    private List<TransactionDto> transactions;
}
