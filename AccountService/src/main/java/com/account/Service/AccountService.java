    package com.account.Service;

    import com.account.DTO.AccountDto;
    import com.account.DTO.TransferRequestDto;
    import com.account.Entities.Account;

    import java.util.List;

    public interface AccountService {
        Account createAccount(AccountDto accountDto);
        List<Account> getAllAccounts();
        Account getAccountById(Long accountId);
        void transferFunds(TransferRequestDto transferRequestDto);
    }
