package org.example.timesheet.dto.leave;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LeaveDto {

    private String name;

    private String type;

    private int applyDay;
}
