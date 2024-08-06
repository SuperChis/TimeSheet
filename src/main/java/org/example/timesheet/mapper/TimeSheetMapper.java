package org.example.timesheet.mapper;

import org.example.timesheet.dto.timesheet.TimeSheetDTO;
import org.example.timesheet.dto.timesheet.TimeSheetRequest;
import org.example.timesheet.entity.TimeSheet;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TimeSheetMapper {
    TimeSheetMapper MAPPER = Mappers.getMapper(TimeSheetMapper.class);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    TimeSheet toModel(TimeSheetRequest request);


    @Mapping(source = "task.title", target = "taskTitle")
    TimeSheetDTO toDTO(TimeSheet timeSheet);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateTimeSheetFromRequest(TimeSheetRequest request, @MappingTarget TimeSheet timeSheet);
}
