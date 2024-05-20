package com.account.Controller;

import com.account.DTO.AccountDto;
import com.account.DTO.TransferRequestDto;
import com.account.Entities.Account;
import com.account.Service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AccountControllerTest {

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountController accountController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddAccount() {
        AccountDto accountDto = new AccountDto(1L, 1L, "Savings", "Active", new Date(System.currentTimeMillis()), BigDecimal.valueOf(1000), null);
        Account account = new Account(1L, 1L, "Savings", "Active", new Date(System.currentTimeMillis()), BigDecimal.valueOf(1000), null);

        when(accountService.createAccount(any(AccountDto.class))).thenReturn(account);

        ResponseEntity<Account> response = accountController.addAccount(accountDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(account, response.getBody());
        verify(accountService, times(1)).createAccount(any(AccountDto.class));
    }

    @Test
    public void testTransferFundsSuccess() {
        TransferRequestDto transferRequestDto = new TransferRequestDto(1L, 2L, BigDecimal.valueOf(500));

        doNothing().when(accountService).transferFunds(any(TransferRequestDto.class));

        ResponseEntity<String> response = accountController.transferFunds(transferRequestDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Transfer successful", response.getBody());
        verify(accountService, times(1)).transferFunds(any(TransferRequestDto.class));
    }

    @Test
    public void testTransferFundsInvalidSenderAccount() {
        TransferRequestDto transferRequestDto = new TransferRequestDto(1L, 2L, BigDecimal.valueOf(500));
        doThrow(new RuntimeException("Invalid sender account")).when(accountService).transferFunds(any(TransferRequestDto.class));

        ResponseEntity<String> response = accountController.transferFunds(transferRequestDto);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid sender account", response.getBody());
    }

    @Test
    public void testGetAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account(1L, 1L, "Savings", "Active", new Date(System.currentTimeMillis()), BigDecimal.valueOf(1000), null));

        when(accountService.getAllAccounts()).thenReturn(accounts);

        ResponseEntity<List<Account>> response = accountController.getAllAccounts();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(accounts, response.getBody());
        verify(accountService, times(1)).getAllAccounts();
    }

    @Test
    public void testGetAccountById() {
        Account account = new Account(1L, 1L, "Savings", "Active", new Date(System.currentTimeMillis()), BigDecimal.valueOf(1000), null);
        when(accountService.getAccountById(anyLong())).thenReturn(account);

        ResponseEntity<?> response = accountController.getAccountById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(account, response.getBody());
        verify(accountService, times(1)).getAccountById(anyLong());
    }

    @Test
    public void testGetAccountByIdNotFound() {
        when(accountService.getAccountById(anyLong())).thenThrow(new RuntimeException("Account not found"));

        ResponseEntity<?> response = accountController.getAccountById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Account not found: Account not found", response.getBody());
    }

    @Test
    public void testGetAccountsByCustomerId() {
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account(1L, 1L, "Savings", "Active", new Date(System.currentTimeMillis()), BigDecimal.valueOf(1000), null));

        when(accountService.getAccountsByCustomerId(anyLong())).thenReturn(accounts);

        ResponseEntity<?> response = accountController.getAccountsByCustomerId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(accounts, response.getBody());
        verify(accountService, times(1)).getAccountsByCustomerId(anyLong());
    }

    @Test
    public void testGetAccountsByCustomerIdNotFound() {
        when(accountService.getAccountsByCustomerId(anyLong())).thenThrow(new RuntimeException("No accounts found for customer ID"));

        ResponseEntity<?> response = accountController.getAccountsByCustomerId(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("No accounts found for customer ID: 1", response.getBody());
    }
}
