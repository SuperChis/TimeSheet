package org.example.timesheet.dto.auth;

import lombok.Data;

@Data
public class ResetPasswordRequest {
    private Long userId;
    private String password;
}
