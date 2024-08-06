package org.example.timesheet.dto.timesheet;

import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.example.timesheet.entity.Task;
import org.example.timesheet.enums.EWorkingType;

import java.util.Date;

@Data
public class TimeSheetRequest {
    private Long id;

    private Long userId;

    private Long hour;

    private String note;

    private EWorkingType type;

    private String isApproved;

    private Date workingDay;

    private Long taskId;
}
