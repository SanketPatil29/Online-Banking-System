package com.user.Service;

import com.user.Entities.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(url="http://localhost:8082",value="Account-Client")
public interface AccountClient {
    @GetMapping("api/accounts/customer/{customerId}")
    List<Account> getAccountsOfClient(@PathVariable Long customerId);

    @PostMapping("api/accounts")
    Account createAccount(@RequestBody Account account);



}
