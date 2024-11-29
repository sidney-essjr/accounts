package com.bank.accounts.web.mapper;

import com.bank.accounts.entity.Customer;
import com.bank.accounts.web.dto.CustomerDto;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerMapper {

    public static Customer mapToCustomer(CustomerDto dto) {
        return new ModelMapper().map(dto, Customer.class);
    }

    public static CustomerDto mapToCustomerDto(Customer customer) {
        return new ModelMapper().map(customer, CustomerDto.class);
    }

    public static List<CustomerDto> mapToListCustomerDto(Page<Customer> customers) {
        return customers.stream().map(CustomerMapper::mapToCustomerDto).collect(Collectors.toList());
    }
}
