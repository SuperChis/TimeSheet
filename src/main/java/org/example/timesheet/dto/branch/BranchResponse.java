package org.example.timesheet.dto.branch;

import lombok.Data;
import lombok.experimental.Accessors;
import org.example.timesheet.base.BaseResponse;

import java.util.List;

@Data
@Accessors(chain = true)
public class BranchResponse extends BaseResponse {
    private List<BranchDTO> branchDTOList;
    private BranchDTO branchDTO;

    public BranchResponse(boolean success, int code, String message) {
        super(success, code, message);
    }

    public BranchResponse() {
    }

    public BranchResponse(boolean success, int code) {
        super(success, code);
    }

    public BranchResponse(boolean success, int code, String message, List<BranchDTO> branchDTOList, BranchDTO branchDTO) {
        super(success, code, message);
        this.branchDTOList = branchDTOList;
        this.branchDTO = branchDTO;
    }

    public BranchResponse(List<BranchDTO> branchDTOList, BranchDTO branchDTO) {
        this.branchDTOList = branchDTOList;
        this.branchDTO = branchDTO;
    }

    public BranchResponse(boolean success, int code, List<BranchDTO> branchDTOList, BranchDTO branchDTO) {
        super(success, code);
        this.branchDTOList = branchDTOList;
        this.branchDTO = branchDTO;
    }
}
