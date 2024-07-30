package org.example.timesheet.dto.task;

import lombok.Data;

@Data
public class TaskRequest {
    private Long id;

    private String title;

    private String description;

    private String status;

    private Long projectID;

    private Long idDev;
}
