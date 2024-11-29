package com.bank.accounts.web.mapper;

import com.bank.accounts.entity.Account;
import com.bank.accounts.web.dto.AccountDto;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class AccountMapper {
    public static Account mapToAccount(AccountDto dto) {
        return new ModelMapper().map(dto, Account.class);
    }

    public static AccountDto mapToAccountDto(Account account) {
        return new ModelMapper().map(account, AccountDto.class);
    }

    public static List<AccountDto> mapToListAccountDto(Page<Account> accounts) {
        return accounts.stream().map(AccountMapper::mapToAccountDto).collect(Collectors.toList());
    }
}
