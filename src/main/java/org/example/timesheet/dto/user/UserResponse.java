package org.example.timesheet.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.example.timesheet.base.BaseResponse;
import org.example.timesheet.dto.pagination.PageDto;

import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class UserResponse extends BaseResponse {
    private UserDTO userDTO;
    private List<UserDTO> userDTOList;
    private PageDto pageDto;

    public UserResponse(boolean success, int code, String message) {
        super(success, code, message);
    }

    public UserResponse(boolean success, int code) {
        super(success, code);
    }
}
