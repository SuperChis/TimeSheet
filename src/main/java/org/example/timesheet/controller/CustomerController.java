package org.example.timesheet.controller;

import org.example.timesheet.base.BaseResponse;
import org.example.timesheet.dto.branch.BranchRequest;
import org.example.timesheet.dto.branch.BranchResponse;
import org.example.timesheet.dto.customer.CustomerRequest;
import org.example.timesheet.dto.customer.CustomerResponse;
import org.example.timesheet.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    CustomerService service;

    @PostMapping("/admin/create")
    public BaseResponse createCustomer(@RequestBody CustomerRequest request){
        return service.createCustomerByAdmin(request);
    }

    @GetMapping("/admin")
    public CustomerResponse getCustomersByAdmin(@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size){
        return service.getAllCustomer(PageRequest.of(page, size));
    }

    @PutMapping("/admin/update")
    public CustomerResponse updateBranchByAdmin(@RequestBody CustomerRequest request){
        return service.updateCustomer(request);
    }

    @DeleteMapping("/admin/delete/{id}")
    public CustomerResponse deleteCustomerByAdmin(@PathVariable("id") Long id){
        return service.deleteCustomer(id);
    }
}
