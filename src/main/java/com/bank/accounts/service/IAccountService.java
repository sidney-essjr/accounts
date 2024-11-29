package com.bank.accounts.service;

import com.bank.accounts.exception.ResourceNotFoundException;
import com.bank.accounts.web.dto.CustomerDto;

public interface IAccountService {
    /**
     * Creates a new account for the given customer.
     *
     * @param dto the CustomerDto object containing customer details
     */
    void createAccount(CustomerDto dto);

    /**
     * Finds the customer details based on the provided mobile number.
     *
     * @param mobileNumber the mobile number to search for
     * @return the CustomerDto containing customer details
     * @throws ResourceNotFoundException if no customer is found with the given mobile number
     */
    CustomerDto findAccountByMobileNumber(String mobileNumber);

    /**
     * Updates the account details for the given customer.
     *
     * @param dto the CustomerDto object containing updated account details
     * @return true if the account update is successful, false otherwise
     * @throws ResourceNotFoundException if the account or customer is not found
     */
    boolean updateAccount(CustomerDto dto);

    /**
     * Deletes the account for the given customer.
     *
     * @param mobileNumber the mobile number of the customer
     * @return true if the account deletion is successful, false otherwise
     */
    boolean deleteAccount(String mobileNumber);


}
