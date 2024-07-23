package org.example.timesheet.mapper;

import org.example.timesheet.dto.branch.BranchDTO;
import org.example.timesheet.dto.branch.BranchRequest;
import org.example.timesheet.entity.Branch;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BranchMapper {
    BranchMapper MAPPER = Mappers.getMapper(BranchMapper.class);

    Branch toModel(BranchRequest request);

    BranchDTO toDTO(Branch branch);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateBranchFromRequest(BranchRequest request, @MappingTarget Branch branch);
}
