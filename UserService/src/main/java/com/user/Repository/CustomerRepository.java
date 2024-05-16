package com.user.Repository;

import com.user.Entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    @Query("SELECT c.customer_id FROM Customer c WHERE c.user.username = :username")
    Long findCustomerIdByUsername(@Param("username") String username);


}
