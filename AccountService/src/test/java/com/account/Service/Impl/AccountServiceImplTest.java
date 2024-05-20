package com.account.Service.Impl;

import com.account.DTO.AccountDto;
import com.account.DTO.CustomerDto;
import com.account.DTO.TransferRequestDto;
import com.account.Entities.Account;
import com.account.Repository.AccountRepository;
import com.account.Repository.TransactionRepository;
import com.account.Service.NotificationClient;
import com.account.Service.UserServiceClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private UserServiceClient userServiceClient;

    @Mock
    private NotificationClient notificationClient;

    @InjectMocks
    private AccountServiceImpl accountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createAccount_ValidInput_ReturnsAccount() {
        // Arrange
        AccountDto accountDto = new AccountDto(null, 1L, "Savings", "Active",
                Date.valueOf("2022-01-01"), BigDecimal.valueOf(1000), Collections.emptyList());
        Account account = new Account(null, 1L, "Savings", "Active",
                Date.valueOf("2022-01-01"), BigDecimal.valueOf(1000), null);
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        // Act
        Account result = accountService.createAccount(accountDto);

        // Assert
        assertNotNull(result);
        assertEquals("Savings", result.getType());
        assertEquals(BigDecimal.valueOf(1000), result.getBalance());
        assertEquals("Active", result.getStatus());

    }

    @Test
    void getAccountsByCustomerId_ValidCustomerId_ReturnsAccounts() {
        // Arrange
        Long customerId = 1L;
        Account account = new Account(1L, 1L, "Savings", "Active",
                Date.valueOf("2022-01-01"), BigDecimal.valueOf(1000), null);
        when(accountRepository.findByCustomer_id(customerId)).thenReturn(Collections.singletonList(account));

        // Act
        List<Account> result = accountService.getAccountsByCustomerId(customerId);

        // Assert
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals("Savings", result.get(0).getType());
    }

    @Test
    void transferFunds_ValidTransfer_TransferSuccessful() {
        // Arrange
        TransferRequestDto transferRequestDto = new TransferRequestDto(1L, 2L, BigDecimal.valueOf(500));
        Account senderAccount = new Account(1L, 1L, "Savings", "Active",
                Date.valueOf("2022-01-01"), BigDecimal.valueOf(1000), null);
        Account recipientAccount = new Account(2L, 2L, "Savings", "Active",
                Date.valueOf("2022-01-01"), BigDecimal.valueOf(500), null);
        when(accountRepository.findById(1L)).thenReturn(Optional.of(senderAccount));
        when(accountRepository.findById(2L)).thenReturn(Optional.of(recipientAccount));
        // Creating a new CustomerDto object
        CustomerDto customerDto = new CustomerDto();
        customerDto.setFirstname("John");
        customerDto.setLastname("Doe");
        customerDto.setEmail("john@example.com");

        when(userServiceClient.getCustomerById(anyLong())).thenReturn(customerDto);
        doNothing().when(notificationClient).sendNotification(any());

        // Act
        assertDoesNotThrow(() -> accountService.transferFunds(transferRequestDto));

        // Assert
        assertEquals(BigDecimal.valueOf(500), senderAccount.getBalance());
        assertEquals(BigDecimal.valueOf(1000), recipientAccount.getBalance());
        verify(transactionRepository, times(2)).save(any());
        verify(notificationClient, times(1)).sendNotification(any());
    }
}
