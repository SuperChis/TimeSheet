package org.example.timesheet.mapper;

import org.example.timesheet.dto.position.PositionDTO;
import org.example.timesheet.dto.position.PositionRequest;
import org.example.timesheet.dto.project.ProjectDTO;
import org.example.timesheet.dto.project.ProjectRequest;
import org.example.timesheet.entity.Project;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProjectMapper {
    ProjectMapper MAPPER = Mappers.getMapper(ProjectMapper.class);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Project toModel(ProjectRequest request);


    @Mapping(source = "customer.name", target = "customerName")
    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "customer.address", target = "customerAddress")
    ProjectDTO toDTO(Project project);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProjectFromRequest(ProjectRequest request, @MappingTarget Project project);
}
