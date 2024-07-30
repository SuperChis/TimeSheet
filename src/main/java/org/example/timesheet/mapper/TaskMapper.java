package org.example.timesheet.mapper;

import org.example.timesheet.dto.project.ProjectDTO;
import org.example.timesheet.dto.project.ProjectRequest;
import org.example.timesheet.dto.task.TaskDTO;
import org.example.timesheet.dto.task.TaskRequest;
import org.example.timesheet.entity.Project;
import org.example.timesheet.entity.Task;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TaskMapper {
    TaskMapper MAPPER = Mappers.getMapper(TaskMapper.class);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Task toModel(TaskRequest request);


    @Mapping(source = "project.name", target = "projectName")
//    @Mapping(source = "userWasAssign.name", target = "nameDev")
    TaskDTO toDTO(Task task);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateTaskFromRequest(TaskRequest request, @MappingTarget Task task);
}
