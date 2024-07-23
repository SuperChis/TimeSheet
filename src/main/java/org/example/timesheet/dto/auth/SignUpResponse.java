package org.example.timesheet.dto.auth;
import org.example.timesheet.base.BaseResponse;
import lombok.Data;
@Data
public class SignUpResponse extends BaseResponse {
    public SignUpResponse(boolean success, int code, String mess) {
        super(success, code, mess);
    }
    public SignUpResponse(boolean success, int code) {
        super(success, code);
    }
}
