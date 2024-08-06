package org.example.timesheet.dto.timesheet;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.example.timesheet.base.BaseResponse;
import org.example.timesheet.dto.pagination.PageDto;

import java.util.List;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class TimeSheetResponse extends BaseResponse {
    private TimeSheetDTO dto;
    private List<TimeSheetDTO> timeSheetDTOList;
    private PageDto pageDto;

    public TimeSheetResponse(boolean success, int code) {
        super(success, code);
    }

    public TimeSheetResponse(boolean success, int code, String message) {
        super(success, code, message);
    }
}
