package com.bank.accounts.web.dto;

import lombok.Data;

@Data
public class AccountDto {

    private String accountNumber;
    private String accountType;
    private String branchAddress;
}
