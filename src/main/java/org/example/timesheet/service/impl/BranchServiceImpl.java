package org.example.timesheet.service.impl;

import org.example.timesheet.base.BaseResponse;
import org.example.timesheet.dto.branch.BranchDTO;
import org.example.timesheet.dto.branch.BranchRequest;
import org.example.timesheet.dto.branch.BranchResponse;
import org.example.timesheet.entity.Branch;
import org.example.timesheet.exception.NotFoundException;
import org.example.timesheet.exception.RequetFailException;
import org.example.timesheet.mapper.BranchMapper;
import org.example.timesheet.repository.BranchRepository;
import org.example.timesheet.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class BranchServiceImpl implements BranchService {

    @Autowired
    BranchRepository repository;

    @Override
    public BaseResponse createBranch(BranchRequest request) {
        Branch checkBranch = repository.findByNameAndIsDeletedFalse(request.getName());
        if (checkBranch != null) {
            throw new RequetFailException(false, 400, "branch existed");
        }
        Branch branch = BranchMapper.MAPPER.toModel(request);
        branch.setDeleted(false);
        repository.save(branch);
        return new BaseResponse(true, 200, "create branch successfully");
    }

    @Override
    public BranchResponse getAll() {
        List<Branch> branches = repository.findByIsDeletedFalse();
        List<BranchDTO> dtos = branches.stream().map(BranchMapper.MAPPER::toDTO).collect(Collectors.toList());
        return new BranchResponse(true, 200).setBranchDTOList(dtos);
    }

    @Override
    public BranchResponse updateBranch(BranchRequest request) {
        Branch branch = repository.findByIdAndIsDeletedFalse(request.getId());
        if (branch == null) {
            throw new NotFoundException(false, 404, "Branch not found");
        }
        BranchMapper.MAPPER.updateBranchFromRequest(request, branch);
        repository.save(branch);
        return new BranchResponse(true, 200).setBranchDTO(BranchMapper.MAPPER.toDTO(branch));
    }

    @Override
    public BranchResponse deleteBranch(Long id) {
        Branch branch = repository.findByIdAndIsDeletedFalse(id);
        if (branch == null) {
            throw new NotFoundException(false, 404, "Branch not found");
        }
        branch.setDeleted(true);
        repository.save(branch);
        return new BranchResponse(true, 200, "delete branch succesfully");
    }

}
