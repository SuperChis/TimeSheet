package org.example.timesheet.mapper;


import org.example.timesheet.dto.position.PositionDTO;
import org.example.timesheet.dto.position.PositionRequest;
import org.example.timesheet.entity.Position;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PositionMapper {
    PositionMapper MAPPER = Mappers.getMapper(PositionMapper.class);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Position toModel(PositionRequest request);

    PositionDTO toDTO(Position position);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePositionFromRequest(PositionRequest request, @MappingTarget Position position);
}
