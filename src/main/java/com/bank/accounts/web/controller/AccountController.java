package com.bank.accounts.web.controller;

import com.bank.accounts.constants.AccountConstants;
import com.bank.accounts.service.IAccountService;
import com.bank.accounts.web.dto.CustomerDto;
import com.bank.accounts.web.dto.ResponseDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Validated
@RequestMapping(path = "/api/v1/accounts", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AccountController {

    private final IAccountService accountService;

    @PostMapping(path = "/create", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto dto) {
        accountService.createAccount(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));
    }


    @GetMapping(path = "/find")
    public ResponseEntity<CustomerDto> findAccountByMobileNumber(
            @RequestParam("mobileNumber")
            @Pattern(regexp = "^(?:\\d{11}|)$", message = "Mobile number must be 11 digits")
            String mobileNumber) {
        CustomerDto customerDto = accountService.findAccountByMobileNumber(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDto);
    }

    @PutMapping(path = "/update", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResponseDto> updateAccount(@Valid @RequestBody CustomerDto dto) {
        boolean isUpdated = accountService.updateAccount(dto);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(AccountConstants.STATUS_500, AccountConstants.MESSAGE_500));
        }
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<ResponseDto> deleteAccount(
            @RequestParam("mobileNumber")
            @Pattern(regexp = "^(?:\\d{11}|)$", message = "Mobile number must be 11 digits")
            String mobileNumber) {
        boolean isDeleted = accountService.deleteAccount(mobileNumber);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(AccountConstants.STATUS_500, AccountConstants.MESSAGE_500));
        }
    }
}
