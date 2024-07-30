package org.example.timesheet.service.impl;

import jakarta.transaction.Transactional;
import org.example.timesheet.dto.pagination.PageDto;
import org.example.timesheet.dto.task.TaskRequest;
import org.example.timesheet.dto.task.TaskResponse;
import org.example.timesheet.entity.Project;
import org.example.timesheet.entity.Task;
import org.example.timesheet.entity.User;
import org.example.timesheet.exception.NotFoundException;
import org.example.timesheet.mapper.TaskMapper;
import org.example.timesheet.repository.ProjectRepository;
import org.example.timesheet.repository.TaskRepository;
import org.example.timesheet.repository.UserRepository;
import org.example.timesheet.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository repository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public TaskResponse createTaskByAdmin(TaskRequest request) {
        Project project = projectRepository.findByIdAndIsActiveTrue(request.getProjectID()) ;
        if (project == null ) {
            throw new NotFoundException(false, 404, "Project not found");
        }

        Optional<User> user = Optional.ofNullable(userRepository.findByIdAndIsDeletedFalse(
                        request.getIdDev())
                .orElseThrow(() -> new NotFoundException(false, 404, "user not exists")));

        Task newTask = new Task().setDeleted(false)
                .setDescription(request.getDescription())
                .setTitle(request.getTitle())
                .setStatus(request.getStatus())
                .setProject(project)
                .setUserWasAssigned(user.get());

        repository.save(newTask);

        return new TaskResponse(true, 200, "create new task successfully");
    }

    @Override
    public TaskResponse getTaskByAdmin(Pageable pageable) {
        Page<Task> tasks = repository.findAll(pageable);
        PageDto pageDto = PageDto.populatePageDto(tasks);
        List<Task> taskList = tasks.getContent();
        return new TaskResponse(true, 200)
                .setTaskDTOS(taskList.stream()
                                    .map(TaskMapper.MAPPER::toDTO)
                        .collect(Collectors.toList()))
                .setPageDto(pageDto);
    }

    @Override
    @Transactional
    public TaskResponse updateTaskInforByAdmin(TaskRequest request) {
        Task task = repository.findByIdAndIsDeletedFalse(request.getId());
        if(task == null) {
            throw new NotFoundException(false, 404, "Task not found");
        }

        Project project = projectRepository.findByIdAndIsActiveTrue(request.getProjectID()) ;
        if (project == null ) {
            throw new NotFoundException(false, 404, "Project not found");
        }

        TaskMapper.MAPPER.updateTaskFromRequest(request, task);
        return new TaskResponse(true, 200);
    }

    @Override
    @Transactional
    public TaskResponse deactiveTaskByAdmin(Long id) {
        Task task = repository.findByIdAndIsDeletedFalse(id);
        if(task == null) {
            throw new NotFoundException(false, 404, "Task not found");
        }
        task.setDeleted(true);
        return new TaskResponse(true, 200);
    }
}
