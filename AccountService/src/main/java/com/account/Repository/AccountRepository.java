package com.account.Repository;

import com.account.Entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepository extends JpaRepository <Account,Long>{
    // Custom method to find accounts by customer_id

    @Query("select a from Account a where a.customer_id=:customerId ")
    List<Account> findByCustomer_id(@Param("customerId") Long customerId);



}
