package org.example.timesheet.dto.task;

import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class TaskDTO {
    private Long id;

    private String title;

    private String description;

    private String status;

    private boolean isDeleted;

    private String nameDev;

    private String projectName;
}
