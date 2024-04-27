package com.account.mapper;

import com.account.DTO.AccountDto;
import com.account.Entities.Account;

public class AccountMapper {
    public static Account mapToAccount(AccountDto accountDto){
        Account account = new Account();
        account.setCustomer_id(accountDto.getCustomerId());
        account.setType(accountDto.getType());
        account.setStatus(accountDto.getStatus());
        account.setDateOpened(accountDto.getDateOpened());
        account.setBalance(accountDto.getBalance());

        return account;
    }
}
