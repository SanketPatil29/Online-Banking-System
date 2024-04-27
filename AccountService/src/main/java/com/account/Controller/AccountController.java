package com.account.Controller;

import com.account.DTO.AccountDto;
import com.account.DTO.TransferRequestDto;
import com.account.Entities.Account;
import com.account.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private AccountService accountService;
    @Autowired

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    //ADD ACCOUNT REST API
    @PostMapping
    public ResponseEntity<Account> addAccount(@RequestBody AccountDto accountDto){

        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }



    // Transfer Funds REST API
    @PostMapping("/transfer")
    public ResponseEntity<String> transferFunds(@RequestBody TransferRequestDto transferRequestDto) {
        try {
            accountService.transferFunds(transferRequestDto);
            return ResponseEntity.ok("Transfer successful");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Transfer failed: " + e.getMessage());
        }
    }

    // GET ACCOUNT BY ID REST API
    @GetMapping("/{accountId}")
    public ResponseEntity<?> getAccountById(@PathVariable Long accountId) {
        try {
            Account account = accountService.getAccountById(accountId);
            return ResponseEntity.ok(account);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found: " + e.getMessage());
        }
    }


}
