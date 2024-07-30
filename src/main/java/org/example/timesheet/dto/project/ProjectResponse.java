package org.example.timesheet.dto.project;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.example.timesheet.base.BaseResponse;
import org.example.timesheet.dto.pagination.PageDto;

import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class ProjectResponse extends BaseResponse {
    private ProjectDTO projectDTO;

    private List<ProjectDTO> projectDTOList;

    private PageDto pageDto;

    public ProjectResponse(boolean success, int code) {
        super(success, code);
    }

    public ProjectResponse(boolean success, int code, String message) {
        super(success, code, message);
    }
}
