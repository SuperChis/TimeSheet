package org.example.timesheet.controller;

import org.example.timesheet.dto.task.TaskRequest;
import org.example.timesheet.dto.task.TaskResponse;
import org.example.timesheet.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskService service;

    @PostMapping("/admin/create")
    public TaskResponse createProjectByAdmin (@RequestBody TaskRequest request) {
        return service.createTaskByAdmin(request);
    }

    @GetMapping("/admin")
    public TaskResponse getProjectListByAdmin(Pageable pageable){
        return service.getTaskByAdmin(pageable);
    }

    @PutMapping("/admin/update")
    public TaskResponse editProjectInforByAdmin(@RequestBody TaskRequest request){
        return service.updateTaskInforByAdmin(request);
    }

    @DeleteMapping("/admin/remove/{id}")
    public TaskResponse deactiveProjecByAdmin(@PathVariable("id") Long id){
        return service.deactiveTaskByAdmin(id);
    }
}
