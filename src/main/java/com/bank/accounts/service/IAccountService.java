package com.bank.accounts.service;

import com.bank.accounts.web.dto.CustomerDto;

public interface IAccountService {
    /**
     * @param dto - CustomerDto object
     */
    void createAccount(CustomerDto dto);

    /**
     * @param mobileNumber - String object
     * @return CustomerDto
     */
    CustomerDto findAccountByMobileNumber(String mobileNumber);

    /**
     * @param dto - CustomerDto Object
     * @return boolean indicating if the update of Account details is successful or not
     */
    boolean updateAccount(CustomerDto dto);
}
