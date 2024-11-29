package com.bank.accounts.web.dto;

import lombok.Data;

@Data
public class AccountDto {

    private Long accountNumber;
    private String accountType;
    private String branchAddress;
}
