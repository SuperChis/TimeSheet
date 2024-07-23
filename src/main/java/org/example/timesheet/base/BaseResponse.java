package org.example.timesheet.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BaseResponse {
    private  boolean success;
    private int code;
    private String message;
    public BaseResponse(boolean success, int code) {
        this.success = success;
        this.code = code;
    }

    public BaseResponse(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }
}
