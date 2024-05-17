    package com.account.Service;

    import com.account.DTO.CustomerDto;
    import org.springframework.cloud.openfeign.FeignClient;
    import org.springframework.stereotype.Component;
    import org.springframework.stereotype.Service;
    import org.springframework.web.bind.annotation.CrossOrigin;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.PathVariable;

    @FeignClient(name = "UserService") // Configure the URL of UserService
    @Service
    public interface UserServiceClient {

        @GetMapping("/api/customer/{customerId}") // Define the endpoint for fetching customer details by ID
        CustomerDto getCustomerById(@PathVariable("customerId") Long customerId);
    }
