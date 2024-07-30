package org.example.timesheet.dto.position;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.example.timesheet.base.BaseResponse;
import org.example.timesheet.dto.pagination.PageDto;

import java.util.List;
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class PositionResponse extends BaseResponse {
    private PositionDTO positionDTO;
    private List<PositionDTO> positionDTOList;
    private PageDto pageDto;

    public PositionResponse(boolean success, int code) {
        super(success, code);
    }

    public PositionResponse(boolean success, int code, String message) {
        super(success, code, message);
    }
}
