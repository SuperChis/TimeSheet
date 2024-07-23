package org.example.timesheet.service;


import org.example.timesheet.base.BaseResponse;
import org.example.timesheet.dto.branch.BranchRequest;
import org.example.timesheet.dto.branch.BranchResponse;

import java.io.IOException;

public interface BranchService {
    BaseResponse createBranch(BranchRequest request);

    BranchResponse getAll();

    BranchResponse updateBranch(BranchRequest request);

    BranchResponse deleteBranch(Long id);
}
