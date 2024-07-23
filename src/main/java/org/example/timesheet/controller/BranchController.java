package org.example.timesheet.controller;

import org.example.timesheet.base.BaseResponse;
import org.example.timesheet.dto.branch.BranchRequest;
import org.example.timesheet.dto.branch.BranchResponse;
import org.example.timesheet.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/branch")
public class BranchController {

    @Autowired
    BranchService service;

    @PostMapping("/admin/create")
    public BaseResponse createBranch(@RequestBody BranchRequest request){
        return service.createBranch(request);
    }

    @GetMapping("/admin/")
    public BranchResponse getAllBranch(){
        return service.getAll();
    }

    @PutMapping("/admin/update")
    public BranchResponse updateBranchByAdmin(@RequestBody BranchRequest request){
        return service.updateBranch(request);
    }

    @DeleteMapping("/admin/delete/{id}")
    public BranchResponse deleteBranchByAdmin(@PathVariable("id") Long id){
        return service.deleteBranch(id);
    }
}
