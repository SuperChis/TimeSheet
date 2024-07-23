package org.example.timesheet.dto.leave;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.example.timesheet.base.BaseResponse;

import java.util.List;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class LeaveResponse extends BaseResponse {

    private LeaveDto leaveDto;

    private List<LeaveDto> leaveDtoList;

    public LeaveResponse(boolean success, int code) {
        super(success, code);
    }

    public LeaveResponse(boolean success, int code, String message) {
        super(success, code, message);
    }
}
