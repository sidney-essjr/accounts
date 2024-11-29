package com.bank.accounts.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountDto {

    @NotBlank(message = "AccountNumber can not be a null or empty")
    @Pattern(regexp = "^(?:\\d{10}|)$", message = "AccountNumber must be 10 digits")
    private Long accountNumber;

    @NotBlank(message = "AccountType can not be a null or empty")
    private String accountType;
    @NotBlank(message = "BranchAddress can not be a null or empty")
    private String branchAddress;
}
