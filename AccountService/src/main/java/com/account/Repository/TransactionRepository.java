package com.account.Repository;

import com.account.Entities.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transactions ,Long> {
}
