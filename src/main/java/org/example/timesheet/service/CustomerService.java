package org.example.timesheet.service;

import org.example.timesheet.base.BaseResponse;
import org.example.timesheet.dto.branch.BranchRequest;
import org.example.timesheet.dto.branch.BranchResponse;
import org.example.timesheet.dto.customer.CustomerRequest;
import org.example.timesheet.dto.customer.CustomerResponse;
import org.springframework.data.domain.Pageable;

public interface CustomerService {
    BaseResponse createCustomerByAdmin(CustomerRequest request);

    CustomerResponse getAllCustomer(Pageable pageable);

    CustomerResponse updateCustomer(CustomerRequest request);

    CustomerResponse deleteCustomer(Long id);
}
