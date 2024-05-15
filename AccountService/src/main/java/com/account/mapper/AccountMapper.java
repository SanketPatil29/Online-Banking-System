package com.account.mapper;

import com.account.DTO.AccountDto;
import com.account.Entities.Account;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

public class AccountMapper {
    public static Account mapToAccount(AccountDto accountDto){
        Account account = new Account();
        account.setCustomer_id(accountDto.getCustomerId());
        account.setType(accountDto.getType());
        // Set default values if not provided in DTO
        account.setDateOpened(accountDto.getDateOpened() != null ? accountDto.getDateOpened() : Date.valueOf(LocalDate.now()));
        account.setBalance(accountDto.getBalance() != null ? accountDto.getBalance() : new BigDecimal("1000.00"));
        account.setStatus(accountDto.getStatus() != null ? accountDto.getStatus() : "ACTIVE");

        return account;
    }
}


//        account.setStatus(accountDto.getStatus());
//        account.setDateOpened(accountDto.getDateOpened());
//        account.setBalance(accountDto.getBalance());