package com.bank.accounts.repository;

import com.bank.accounts.entity.Account;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    /**
     * @param customerId String represents the customer id
     * @return Optional Account
     */
    Optional<Account> findByCustomerId(Long customerId);

    /**
     * @param accountNumber String represents the accountNumber
     * @return Optional Account
     */
    Optional<Account> findByAccountNumber(Long accountNumber);

    /**
     * Deletes the account associated with the given customer ID.
     *
     * @param customerId the ID of the customer whose account is to be deleted
     */
    @Transactional
    @Modifying
    void deleteByCustomerId(Long customerId);
}
