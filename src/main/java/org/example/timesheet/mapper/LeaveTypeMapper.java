package org.example.timesheet.mapper;

import org.example.timesheet.dto.customer.CustomerRequest;
import org.example.timesheet.dto.leave.LeaveDto;
import org.example.timesheet.dto.leave.LeaveRequest;
import org.example.timesheet.entity.Customer;
import org.example.timesheet.entity.LeaveType;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LeaveTypeMapper {
    LeaveTypeMapper MAPPER = Mappers.getMapper(LeaveTypeMapper.class);

    LeaveType toModel(LeaveRequest request);

    LeaveDto toDTO(LeaveType leaveType);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateLeaveTypeFromRequest(LeaveRequest request, @MappingTarget LeaveType leaveType);
}
