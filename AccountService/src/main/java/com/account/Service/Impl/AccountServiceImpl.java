package com.account.Service.Impl;

import com.account.DTO.AccountDto;
import com.account.DTO.TransferRequestDto;
import com.account.Entities.Account;
import com.account.Entities.Transactions;

import com.account.Repository.AccountRepository;
import com.account.Repository.TransactionRepository;
import com.account.Service.AccountService;
import com.account.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional

public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;
    private TransactionRepository transactionRepository;
    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Account createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return savedAccount;
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account getAccountById(Long accountId) {

        return accountRepository.findById(accountId).orElseThrow(()->new RuntimeException("Customer Not Found"));

    }

    @Override
    public void transferFunds(TransferRequestDto transferRequestDto) {
        // Implementation for transferring funds
        Long senderAccountId = transferRequestDto.getSenderAccountId();
        Long recipientAccountId = transferRequestDto.getRecipientAccountId();
        BigDecimal amount = transferRequestDto.getAmount();

        Account senderAccount = accountRepository.findById(senderAccountId)
                .orElseThrow(() -> new RuntimeException("Sender account not found"));

        Account recipientAccount = accountRepository.findById(recipientAccountId)
                .orElseThrow(() -> new RuntimeException("Recipient account not found"));

        if (senderAccount.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient funds in the sender account");
        }

        senderAccount.setBalance(senderAccount.getBalance().subtract(amount));
        recipientAccount.setBalance(recipientAccount.getBalance().add(amount));

        Transactions transactionFromSender = new Transactions();
        transactionFromSender.setTx_type("DEBIT");
        transactionFromSender.setAmount(amount.negate()); // Debit from sender
        transactionFromSender.setDescription("Transferred to recipient with account no " + recipientAccountId);
        transactionFromSender.setTransactionDateTime(LocalDateTime.now());
        transactionFromSender.setStatus("SUCCEED");
        transactionFromSender.setAccount(senderAccount);
        transactionRepository.save(transactionFromSender);

        Transactions transactionToRecipient = new Transactions();
        transactionToRecipient.setTx_type("CREDIT");
        transactionToRecipient.setAmount(amount); // Credit to recipient
        transactionToRecipient.setDescription("Received from sender with account no " + senderAccountId);
        transactionToRecipient.setTransactionDateTime(LocalDateTime.now());
        transactionToRecipient.setStatus("SUCCEED");
        transactionToRecipient.setAccount(recipientAccount);
        transactionRepository.save(transactionToRecipient);
    }
}
