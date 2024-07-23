package org.example.timesheet.service.impl;

import org.example.timesheet.base.BaseResponse;
import org.example.timesheet.dto.customer.CustomerDTO;
import org.example.timesheet.dto.customer.CustomerRequest;
import org.example.timesheet.dto.customer.CustomerResponse;
import org.example.timesheet.dto.pagination.PageDto;
import org.example.timesheet.entity.Customer;
import org.example.timesheet.exception.NotFoundException;
import org.example.timesheet.exception.RequetFailException;
import org.example.timesheet.mapper.CustomerMapper;
import org.example.timesheet.repository.CustomerRepository;
import org.example.timesheet.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Override
    public BaseResponse createCustomerByAdmin(CustomerRequest request) {
        if (request.getName() == null) {
            throw new RequetFailException(false, 400, "branch name must be not null");
        }
        Customer checkCustomer = repository.findByNameAndIsDeletedFalse(request.getName());
        if (checkCustomer != null) {
            throw new RequetFailException(false, 400, "Customer exists");
        }
        Customer customer = new Customer().setName(request.getName()).setAddress(request.getAddress());
        repository.save(customer);
        return new BaseResponse(true, 200, "create successfull");
    }

    @Override
    public CustomerResponse getAllCustomer(Pageable pageable) {
        Page<Customer> customerPage = repository.findByIsDeletedFalse(pageable);
        List<Customer> customerList = customerPage.getContent();
        List<CustomerDTO> customerDTOList = customerList.stream()
                .map(CustomerMapper.MAPPER::toDTO)
                .collect(Collectors.toList());
        PageDto pageDto = PageDto.populatePageDto(customerPage);
        return new CustomerResponse(true, 200).setCustomerDTOList(customerDTOList)
                .setPageDto(pageDto);
    }

    @Override
    public CustomerResponse updateCustomer(CustomerRequest request) {
        Customer customer = repository.findByIdAndIsDeletedFalse(request.getId());
        if (customer == null) {
            throw new NotFoundException(false, 404, "Customer not found");
        }
        CustomerMapper.MAPPER.updateCustomerFromRequest(request, customer);
        repository.save(customer);
        return new CustomerResponse(true, 200).setCustomerDTO(CustomerMapper.MAPPER.toDTO(customer));
    }

    @Override
    public CustomerResponse deleteCustomer(Long id) {
        Customer customer = repository.findByIdAndIsDeletedFalse(id);
        if (customer == null) {
            throw new NotFoundException(false, 404, "Customer not found");
        }
        customer.setDeleted(true);
        repository.save(customer);
        return new CustomerResponse(true, 200, "delete customer succesfully");
    }

}
