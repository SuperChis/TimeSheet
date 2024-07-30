package org.example.timesheet.dto.task;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.example.timesheet.base.BaseResponse;
import org.example.timesheet.dto.pagination.PageDto;

import java.util.List;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class TaskResponse extends BaseResponse {
    private TaskDTO dto;
    private List<TaskDTO> taskDTOS;
    private PageDto pageDto;

    public TaskResponse(boolean success, int code) {
        super(success, code);
    }

    public TaskResponse(boolean success, int code, String message) {
        super(success, code, message);
    }
}
