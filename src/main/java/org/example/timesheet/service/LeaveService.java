package org.example.timesheet.service;

import org.example.timesheet.base.BaseResponse;
import org.example.timesheet.dto.leave.LeaveRequest;
import org.example.timesheet.dto.leave.LeaveResponse;

public interface LeaveService {
    BaseResponse createLeaveType(LeaveRequest request);

    LeaveResponse getAll();

    LeaveResponse updateLeaveType(Long id, LeaveRequest request);

    LeaveResponse deleteLeaveType(Long id);
}
