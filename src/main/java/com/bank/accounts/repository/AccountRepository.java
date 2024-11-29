package com.bank.accounts.repository;

import com.bank.accounts.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    /**
     * @param customerId String represents the customer id
     * @return Optional Account
     */
    Optional<Account> findByCustomerId(Long customerId);
}
