    package com.account.Service;

    import com.account.DTO.AccountDto;
    import com.account.DTO.TransferRequestDto;
    import com.account.Entities.Account;

    import java.util.List;

    public interface AccountService {
        Account createAccount(AccountDto accountDto);
        List<Account> getAllAccounts();
        Account getAccountById(Long accountId);
        List<Account> getAccountsByCustomerId(Long customerId); // New method

        void transferFunds(TransferRequestDto transferRequestDto);
    }
