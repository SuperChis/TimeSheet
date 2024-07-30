package org.example.timesheet.service;

import org.example.timesheet.dto.task.TaskRequest;
import org.example.timesheet.dto.task.TaskResponse;
import org.springframework.data.domain.Pageable;

public interface TaskService {

    TaskResponse createTaskByAdmin(TaskRequest request);

    TaskResponse getTaskByAdmin(Pageable pageable);

    TaskResponse updateTaskInforByAdmin(TaskRequest request);

    TaskResponse deactiveTaskByAdmin(Long id);
}
