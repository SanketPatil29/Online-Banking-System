package com.account.Repository;

import com.account.Entities.Account;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void findByCustomer_id_ValidCustomerId_ReturnsAccounts() {
        // Arrange
        Long customerId = 1L;

        // Add accounts associated with the given customer ID to the in-memory database
        Account account1 = new Account(1L, customerId, "Savings", "Active", Date.valueOf("2022-01-01"), BigDecimal.valueOf(1000), null);
        Account account2 = new Account(2L, customerId, "Checking", "Active", Date.valueOf("2022-01-01"), BigDecimal.valueOf(2000), null);
        accountRepository.saveAll(List.of(account1, account2));

        // Act
        List<Account> accounts = accountRepository.findByCustomer_id(customerId);

        // Assert
        assertNotNull(accounts);
        assertEquals(2, accounts.size());
    }
}
