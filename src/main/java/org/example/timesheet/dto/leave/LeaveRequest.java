package org.example.timesheet.dto.leave;

import lombok.Data;

@Data
public class LeaveRequest {
    private Long id;

    private String name;

    private String type;

    private Integer applyDay;
}
