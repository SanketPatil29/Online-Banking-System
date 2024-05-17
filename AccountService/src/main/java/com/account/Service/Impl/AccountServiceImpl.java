//package com.account.Service.Impl;
//
//import com.account.DTO.AccountDto;
//import com.account.DTO.CustomerDto;
//import com.account.DTO.TransferRequestDto;
//import com.account.Entities.Account;
//import com.account.Entities.Transactions;
//
//import com.account.Repository.AccountRepository;
//import com.account.Repository.TransactionRepository;
//import com.account.Service.AccountService;
//import com.account.mapper.AccountMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Service
//@Transactional
//
//public class AccountServiceImpl implements AccountService {
//
//    private AccountRepository accountRepository;
//    private TransactionRepository transactionRepository;
//    @Autowired
//    public AccountServiceImpl(AccountRepository accountRepository, TransactionRepository transactionRepository) {
//        this.accountRepository = accountRepository;
//        this.transactionRepository = transactionRepository;
//
//    }
//
//    @Override
//    public Account createAccount(AccountDto accountDto) {
//        Account account = AccountMapper.mapToAccount(accountDto);
//        Account savedAccount = accountRepository.save(account);
//        return savedAccount;
//    }
//
//    @Override
//    public List<Account> getAllAccounts() {
//        return accountRepository.findAll();
//    }
//
//    @Override
//    public Account getAccountById(Long accountId) {
//
//        return accountRepository.findById(accountId).orElseThrow(()->new RuntimeException("Customer Not Found"));
//
//    }
//    @Override
//    public List<Account> getAccountsByCustomerId(Long customerId) {
////        return accountRepository.findByCustomerId(customerId);
//        return accountRepository.findByCustomer_id(customerId);
//    }
//
//
//
//
//
//    @Override
//    public void transferFunds(TransferRequestDto transferRequestDto) {
//        // Implementation for transferring funds
//        Long senderAccountId = transferRequestDto.getSenderAccountId();
//        Long recipientAccountId = transferRequestDto.getRecipientAccountId();
//        BigDecimal amount = transferRequestDto.getAmount();
//
//        Account senderAccount = accountRepository.findById(senderAccountId)
//                .orElseThrow(() -> new RuntimeException("Sender account not found"));
//
//        Account recipientAccount = accountRepository.findById(recipientAccountId)
//                .orElseThrow(() -> new RuntimeException("Recipient account not found"));
//
//        if (senderAccount.getBalance().compareTo(amount) < 0) {
//            throw new RuntimeException("Insufficient funds in the sender account");
//        }
//
//        senderAccount.setBalance(senderAccount.getBalance().subtract(amount));
//        recipientAccount.setBalance(recipientAccount.getBalance().add(amount));
//
//        // Fetch the customer details associated with the sender's account
//        CustomerDto senderCustomerDto = userServiceClient.getCustomerById(senderAccount.getCustomerId());
//
//        // Assuming senderCustomerDto.getEmail() returns the email address
//        String senderEmail = senderCustomerDto.getEmail();
//
//        Transactions transactionFromSender = new Transactions();
//        transactionFromSender.setTx_type("DEBIT");
//        transactionFromSender.setAmount(amount.negate()); // Debit from sender
//        transactionFromSender.setDescription("Transferred to recipient with account no " + recipientAccountId);
//        transactionFromSender.setTransactionDateTime(LocalDateTime.now());
//        transactionFromSender.setStatus("SUCCEED");
//        transactionFromSender.setAccount(senderAccount);
//        transactionRepository.save(transactionFromSender);
//
//        Transactions transactionToRecipient = new Transactions();
//        transactionToRecipient.setTx_type("CREDIT");
//        transactionToRecipient.setAmount(amount); // Credit to recipient
//        transactionToRecipient.setDescription("Received from sender with account no " + senderAccountId);
//        transactionToRecipient.setTransactionDateTime(LocalDateTime.now());
//        transactionToRecipient.setStatus("SUCCEED");
//        transactionToRecipient.setAccount(recipientAccount);
//        transactionRepository.save(transactionToRecipient);
//
//
//        // Send notification after successful transfer
//        String message = String.format("Amount of %s successfully transferred from account %s to account %s",
//                amount, senderAccountId, recipientAccountId);
//        NotificationDto notificationDto = new NotificationDto();
//        notificationDto.setRecipientEmail(senderEmail);
//        notificationDto.setSubject("Transfer Successful");
//        notificationDto.setMessage(message);
//        notificationClient.sendNotification(notificationDto);
//    }
//}
package com.account.Service.Impl;

import com.account.DTO.AccountDto;
import com.account.DTO.CustomerDto;
import com.account.DTO.TransferRequestDto;
import com.account.Entities.Account;
import com.account.Entities.Transactions;
import com.account.Repository.AccountRepository;
import com.account.Repository.TransactionRepository;
import com.account.Service.AccountService;
import com.account.mapper.AccountMapper;
import com.account.DTO.NotificationDto;
import com.account.Service.NotificationClient;
import com.account.Service.UserServiceClient;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private  UserServiceClient userServiceClient;
    private  NotificationClient notificationClient;



    @Override
    public Account createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return savedAccount;
    }
    @Override
    public List<Account> getAllAccounts() {
        return null;
    }

    @Override
    public Account getAccountById(Long accountId) {
        return null;
    }

    @Override
    public List<Account> getAccountsByCustomerId(Long customerId) {
//        return accountRepository.findByCustomerId(customerId);
        return accountRepository.findByCustomer_id(customerId);
    }


//    @Override
//    public void transferFunds(TransferRequestDto transferRequestDto) {
//        System.out.println("Inside Transfer");
//        // Implementation for transferring funds
//        Long senderAccountId = transferRequestDto.getSenderAccountId();
//        Long recipientAccountId = transferRequestDto.getRecipientAccountId();
//        BigDecimal amount = transferRequestDto.getAmount();
//
//        Account senderAccount = accountRepository.findById(senderAccountId)
//                .orElseThrow(() -> new RuntimeException("Sender account not found"));
//
//        Account recipientAccount = accountRepository.findById(recipientAccountId)
//                .orElseThrow(() -> new RuntimeException("Recipient account not found"));
//
//        if (senderAccount.getBalance().compareTo(amount) < 0) {
//            throw new RuntimeException("Insufficient funds in the sender account");
//        }
//
//        senderAccount.setBalance(senderAccount.getBalance().subtract(amount));
//        recipientAccount.setBalance(recipientAccount.getBalance().add(amount));
//        long a = senderAccount.getCustomer_id();
//        System.out.println("Setting Balance-- Before Customer DTO"+a);
//        // Fetch the customer details associated with the sender's account
//        CustomerDto senderCustomerDto = userServiceClient.getCustomerById(senderAccount.getCustomer_id());
//        System.out.println("After getting Customer info");
//        // Assuming senderCustomerDto.getEmail() returns the email address
//        String senderEmail = senderCustomerDto.getEmail();
//        System.out.println("After getting sender mail");
//        Transactions transactionFromSender = new Transactions();
//        transactionFromSender.setTx_type("DEBIT");
//        transactionFromSender.setAmount(amount.negate()); // Debit from sender
//        transactionFromSender.setDescription("Transferred to recipient with account no " + recipientAccountId);
//        transactionFromSender.setTransactionDateTime(LocalDateTime.now());
//        transactionFromSender.setStatus("SUCCEED");
//        transactionFromSender.setAccount(senderAccount);
//        transactionRepository.save(transactionFromSender);
//
//        Transactions transactionToRecipient = new Transactions();
//        transactionToRecipient.setTx_type("CREDIT");
//        transactionToRecipient.setAmount(amount); // Credit to recipient
//        transactionToRecipient.setDescription("Received from sender with account no " + senderAccountId);
//        transactionToRecipient.setTransactionDateTime(LocalDateTime.now());
//        transactionToRecipient.setStatus("SUCCEED");
//        transactionToRecipient.setAccount(recipientAccount);
//        transactionRepository.save(transactionToRecipient);
//
//        // Send notification after successful transfer
//        String message = String.format("Amount of %s successfully transferred from account %s to account %s",
//                amount, senderAccountId, recipientAccountId);
//        NotificationDto notificationDto = new NotificationDto();
//        notificationDto.setRecipientEmail(senderEmail);
//        notificationDto.setSubject("Transfer Successful");
//        notificationDto.setMessage(message);
//        notificationClient.sendNotification(notificationDto);
//    }
@Override
public void transferFunds(TransferRequestDto transferRequestDto) {
    // Implementation for transferring funds
    Long senderAccountId = transferRequestDto.getSenderAccountId();
    Long recipientAccountId = transferRequestDto.getRecipientAccountId();
    BigDecimal amount = transferRequestDto.getAmount();

    Account senderAccount = accountRepository.findById(senderAccountId)
            .orElseThrow(() -> new RuntimeException("Invalid sender account"));

    Account recipientAccount = accountRepository.findById(recipientAccountId)
            .orElseThrow(() -> new RuntimeException("Invalid recipient account"));

    if (senderAccount.getBalance().compareTo(amount) < 0) {
        throw new RuntimeException("Insufficient balance in the sender account");
    }

    // Deduct amount from sender's account
    senderAccount.setBalance(senderAccount.getBalance().subtract(amount));
    accountRepository.save(senderAccount);

    // Add amount to recipient's account
    recipientAccount.setBalance(recipientAccount.getBalance().add(amount));
    accountRepository.save(recipientAccount);

    // Save transaction details for sender
    Transactions transactionFromSender = new Transactions();
    transactionFromSender.setTx_type("DEBIT");
    transactionFromSender.setAmount(amount.negate()); // Debit from sender
    transactionFromSender.setDescription("Transferred to recipient with account no " + recipientAccountId);
    transactionFromSender.setTransactionDateTime(LocalDateTime.now());
    transactionFromSender.setStatus("SUCCEED");
    transactionFromSender.setAccount(senderAccount);
    transactionRepository.save(transactionFromSender);

    // Save transaction details for recipient
    Transactions transactionToRecipient = new Transactions();
    transactionToRecipient.setTx_type("CREDIT");
    transactionToRecipient.setAmount(amount); // Credit to recipient
    transactionToRecipient.setDescription("Received from sender with account no " + senderAccountId);
    transactionToRecipient.setTransactionDateTime(LocalDateTime.now());
    transactionToRecipient.setStatus("SUCCEED");
    transactionToRecipient.setAccount(recipientAccount);
    transactionRepository.save(transactionToRecipient);

    // Send notification after successful transfer
    String senderEmail = userServiceClient.getCustomerById(senderAccount.getCustomer_id()).getEmail();
    String message = String.format("Amount of %s successfully transferred from account %s to account %s",
            amount, senderAccountId, recipientAccountId);
    NotificationDto notificationDto = new NotificationDto();
    notificationDto.setRecipientEmail(senderEmail);
    notificationDto.setSubject("Transfer Successful");
    notificationDto.setMessage(message);
    notificationClient.sendNotification(notificationDto);
}

}
