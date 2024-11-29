package com.bank.accounts.service.impl;

import com.bank.accounts.constants.AccountConstants;
import com.bank.accounts.entity.Account;
import com.bank.accounts.entity.Customer;
import com.bank.accounts.exception.CustomerAlreadyExistsException;
import com.bank.accounts.exception.ResourceNotFoundException;
import com.bank.accounts.repository.AccountRepository;
import com.bank.accounts.repository.CustomerRepository;
import com.bank.accounts.service.IAccountService;
import com.bank.accounts.web.dto.CustomerDto;
import com.bank.accounts.web.mapper.AccountMapper;
import com.bank.accounts.web.mapper.CustomerMapper;
import com.bank.accounts.web.utils.Utils;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    /**
     * Creates a new account for the given customer.
     *
     * @param dto the CustomerDto object containing customer details
     * @throws CustomerAlreadyExistsException if a customer with the given mobile number already exists
     */
    @Transactional
    @Override
    public void createAccount(CustomerDto dto) {
        Customer customer = CustomerMapper.mapToCustomer(dto);
        Optional<Customer> findRegisteredMobileNumber =
                customerRepository.findByMobileNumber(dto.getMobileNumber());

        if (findRegisteredMobileNumber.isPresent()) {
            throw new CustomerAlreadyExistsException(
                    String.format("Customer already registered with given mobile number %s",
                            dto.getMobileNumber()));
        }

        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Anonymous");
        Customer savedCustomer = customerRepository.save(customer);
        accountRepository.save(createNewAccount(savedCustomer));
    }

    /**
     * Finds the customer details based on the provided mobile number.
     *
     * @param mobileNumber the mobile number to search for
     * @return the CustomerDto containing customer details
     * @throws ResourceNotFoundException if no customer is found with the given mobile number
     */
    @Transactional
    @Override
    public CustomerDto findAccountByMobileNumber(String mobileNumber) {
        Customer customer = customerRepository
                .findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException(
                                "Customer", "mobileNumber", mobileNumber
                        )
                );
        Account account = accountRepository
                .findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException(
                                "Account", "customerId", customer.getCustomerId().toString()
                        )
                );
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer);
        customerDto.setAccountDto(AccountMapper.mapToAccountDto(account));

        return customerDto;
    }

    /**
     * Updates the account details for the given customer.
     *
     * @param dto the CustomerDto object containing updated account details
     * @return true if the account update is successful, false otherwise
     * @throws ResourceNotFoundException if the account or customer is not found
     */
    @Transactional
    @Override
    public boolean updateAccount(CustomerDto dto) {
        boolean isUpdated = false;

        if (dto.getAccountDto() != null) {
            Account account = accountRepository
                    .findByAccountNumber(dto.getAccountDto().getAccountNumber())
                    .orElseThrow(() -> new ResourceNotFoundException(
                                    "Account", "accountNumber", dto.getAccountDto().getAccountNumber().toString()
                            )
                    );

            BeanUtils
                    .copyProperties(dto.getAccountDto(), account, Utils.getNullPropertyNames(dto.getAccountDto()));

            accountRepository.save(account);

            Customer customer = customerRepository
                    .findById(account.getCustomerId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Customer", "customerId", account.getCustomerId().toString()));

            BeanUtils.copyProperties(dto, customer, Utils.getNullPropertyNames(dto));
            isUpdated = true;
        }
        return isUpdated;
    }

    /**
     * @param customer - Customer object
     * @return the new account details
     */
    private Account createNewAccount(Customer customer) {
        Account account = new Account();
        account.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
        account.setAccountNumber(randomAccNumber);
        account.setAccountType(AccountConstants.SAVINGS);
        account.setBranchAddress(AccountConstants.ADDRESS);
        account.setCreatedAt(LocalDateTime.now());
        account.setCreatedBy("Anonymous");
        return account;
    }
}
