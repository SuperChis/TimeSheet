package org.example.timesheet.mapper;

import org.example.timesheet.dto.branch.BranchRequest;
import org.example.timesheet.dto.customer.CustomerDTO;
import org.example.timesheet.dto.customer.CustomerRequest;
import org.example.timesheet.entity.Branch;
import org.example.timesheet.entity.Customer;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper MAPPER = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO toDTO(Customer customer);

    Customer toModel(CustomerDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCustomerFromRequest(CustomerRequest request, @MappingTarget Customer customer);
}
