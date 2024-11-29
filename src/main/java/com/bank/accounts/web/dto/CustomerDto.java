package com.bank.accounts.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDto {

    @NotBlank(message = "Name can not be a null or empty")
    @Size(min = 3, max = 80, message = "The length of the customer name should be between 3 or 80")
    private String name;

    @NotBlank(message = "E-mail can not be a null or empty")
    @Email(message = "Can be a valid e-mail")
    private String email;

    @Pattern(regexp = "^(?:\\d{11}|)$", message = "Mobile number must be 11 digits")
    private String mobileNumber;
    private AccountDto accountDto;
}
