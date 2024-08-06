package org.example.timesheet.dto.timesheet;

import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.example.timesheet.entity.Task;
import org.example.timesheet.enums.EWorkingType;

import java.util.Date;

@Data
public class TimeSheetDTO {
    private Long id;

    private Long hour;

    private String note;

    private EWorkingType type;

    private boolean isApproved;

    private String startTime;

    private String endTime;

    private Date workingDay;

    private String taskTitle;
}
