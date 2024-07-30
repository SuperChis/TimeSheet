package org.example.timesheet.dto.user;

import lombok.Data;

@Data
public class UserRequest {
    private String email;
    private String username;
    private String startTimeWorking;

    private String endTimeWorking;
}
