package com.bank.accounts.repository;

import com.bank.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    /**
     * @param mobileNumber String represents the mobile number
     * @return Optional Customer
     */
    Optional<Customer> findByMobileNumber(String mobileNumber);
}
