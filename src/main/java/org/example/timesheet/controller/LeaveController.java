package org.example.timesheet.controller;

import org.example.timesheet.base.BaseResponse;
import org.example.timesheet.dto.branch.BranchRequest;
import org.example.timesheet.dto.branch.BranchResponse;
import org.example.timesheet.dto.leave.LeaveRequest;
import org.example.timesheet.dto.leave.LeaveResponse;
import org.example.timesheet.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/leaveType")
public class LeaveController {

    @Autowired
    private LeaveService service;

    @PostMapping("/admin/create")
    public BaseResponse createLeave(@RequestBody LeaveRequest request){
        return service.createLeaveType(request);
    }

    @GetMapping("/admin")
    public LeaveResponse getAllLeaveTypes(){
        return service.getAll();
    }

    @PutMapping("/admin/update/{id}")
    public LeaveResponse updateLeaveByAdmin(@PathVariable("id") Long id,
                                              @RequestBody LeaveRequest request){
        return service.updateLeaveType(id, request);
    }

    @DeleteMapping("/admin/delete/{id}")
    public LeaveResponse deleteBranchByAdmin(@PathVariable("id") Long id){
        return service.deleteLeaveType(id);
    }
}
