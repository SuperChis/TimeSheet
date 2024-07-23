package org.example.timesheet.service.impl;

import org.example.timesheet.base.BaseResponse;
import org.example.timesheet.dto.leave.LeaveDto;
import org.example.timesheet.dto.leave.LeaveRequest;
import org.example.timesheet.dto.leave.LeaveResponse;
import org.example.timesheet.entity.LeaveType;
import org.example.timesheet.exception.RequetFailException;
import org.example.timesheet.mapper.LeaveTypeMapper;
import org.example.timesheet.repository.LeaveTypeRepository;
import org.example.timesheet.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private LeaveTypeRepository repository;

    @Override
    public BaseResponse createLeaveType(LeaveRequest request) {
        if (request.getName() == null || request.getName().trim().equals("")) {
            throw new RequetFailException(false, 400, "Leave type name not empty");
        }
        if (request.getType() == null || request.getType().trim().equals("")) {
            throw new RequetFailException(false, 400, "type of leave not empty");
        }
        if (request.getApplyDay() == null) {
            throw new RequetFailException(false, 400, "apply day must be not null");
        }
        LeaveType leaveType = LeaveTypeMapper.MAPPER.toModel(request);
        leaveType.setDeleted(false);
        repository.save(leaveType);
        return new BaseResponse(true, 200);
    }

    @Override
    public LeaveResponse getAll() {
        List<LeaveType> leaveTypes = repository.findAll();
        List<LeaveDto> dtos = leaveTypes.stream().map(LeaveTypeMapper.MAPPER::toDTO).collect(Collectors.toList());
        return new LeaveResponse(true, 200).setLeaveDtoList(dtos);
    }

    @Override
    public LeaveResponse updateLeaveType(Long id, LeaveRequest request) {
        LeaveType leaveType = repository.findByIdAndIsDeletedFalse(id);
        if (leaveType == null) {
            throw new RequetFailException(false, 400, "Leave type not found");
        }
        LeaveTypeMapper.MAPPER.updateLeaveTypeFromRequest(request, leaveType);
        return new LeaveResponse(true, 200);
    }

    @Override
    public LeaveResponse deleteLeaveType(Long id) {
        LeaveType leaveType = repository.findByIdAndIsDeletedFalse(id);
        if (leaveType == null) {
            throw new RequetFailException(false, 400, "Leave type not found");
        }
        leaveType.setDeleted(true);
        return new LeaveResponse(true, 200);
    }

}
