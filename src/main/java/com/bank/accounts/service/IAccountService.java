package com.bank.accounts.service;

import com.bank.accounts.web.dto.CustomerDto;

public interface IAccountService {
    /**
     * @param dto - CustomerDto object
     */
    void createAccount(CustomerDto dto);

    /**
     * @param mobileNumber - String object
     */
    CustomerDto findAccountByMobileNumber(String mobileNumber);
}
