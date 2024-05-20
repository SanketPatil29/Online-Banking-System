package com.account.Repository;

import com.account.Entities.Account;
import com.account.Entities.Transactions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    void saveTransaction_ValidInput_ReturnsSavedTransaction() {
        // Arrange
        Transactions transaction = new Transactions();
        transaction.setTx_type("DEBIT");
        transaction.setAmount(BigDecimal.valueOf(100));
        transaction.setDescription("Test transaction");
        transaction.setTransactionDateTime(LocalDateTime.now());
        transaction.setStatus("COMPLETED");

        // Act
        Transactions savedTransaction = transactionRepository.save(transaction);

        // Assert
        assertNotNull(savedTransaction);
        assertNotNull(savedTransaction.getTransaction_id()); // Ensure ID is generated
        assertEquals("DEBIT", savedTransaction.getTx_type());
        assertEquals(BigDecimal.valueOf(100), savedTransaction.getAmount());
        assertEquals("Test transaction", savedTransaction.getDescription());
        assertNotNull(savedTransaction.getTransactionDateTime());
        assertEquals("COMPLETED", savedTransaction.getStatus());
    }
}
