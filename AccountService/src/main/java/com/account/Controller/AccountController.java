package com.account.Controller;

import com.account.DTO.AccountDto;
import com.account.DTO.TransferRequestDto;
import com.account.Entities.Account;
import com.account.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // ADD ACCOUNT REST API
    @PostMapping
    public ResponseEntity<Account> addAccount(@RequestBody AccountDto accountDto) {
        try {
            Account createdAccount = accountService.createAccount(accountDto);
            return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transferFunds(@RequestBody TransferRequestDto transferRequestDto) {
        try {
            accountService.transferFunds(transferRequestDto);
            return ResponseEntity.ok("Transfer successful");
        } catch (RuntimeException e) {
            String errorMessage = e.getMessage();
            if ("Invalid sender account".equals(errorMessage)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid sender account");
            } else if ("Invalid recipient account".equals(errorMessage)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid recipient account");
            } else if ("Insufficient balance in the sender account".equals(errorMessage)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient balance in the sender account");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Transfer failed: " + errorMessage);
            }
        }
    }

    // GET ALL ACCOUNTS
    @GetMapping("/all")
    public ResponseEntity<List<Account>> getAllAccounts() {
        try {
            List<Account> accounts = accountService.getAllAccounts();
            return ResponseEntity.ok(accounts);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // GET ACCOUNT BY ACCOUNT ID REST API
    @GetMapping("/{accountId}")
    public ResponseEntity<?> getAccountById(@PathVariable Long accountId) {
        try {
            Account account = accountService.getAccountById(accountId);
            return ResponseEntity.ok(account);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found: " + e.getMessage());
        }
    }

    // GET ACCOUNTS BY CUSTOMER ID REST API
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<?> getAccountsByCustomerId(@PathVariable Long customerId) {
        try {
            List<Account> accounts = accountService.getAccountsByCustomerId(customerId);
            if (accounts.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No accounts found for customer ID: " + customerId);
            } else {
                return ResponseEntity.ok(accounts);
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No accounts found for customer ID: " + customerId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error: " + e.getMessage());
        }
    }
}
