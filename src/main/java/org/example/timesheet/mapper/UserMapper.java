package org.example.timesheet.mapper;

import org.example.timesheet.dto.user.UserDTO;
import org.example.timesheet.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper Mapper = Mappers.getMapper(UserMapper.class);

    UserDTO toDTO(User user);

    User toModel(UserDTO userDTO);

}
