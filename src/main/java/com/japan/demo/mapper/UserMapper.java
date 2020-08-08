package com.japan.demo.mapper;

import com.japan.demo.model.User;
import com.japan.demo.model.dto.UserDto;

public interface UserMapper {
    User userDtoToUser(UserDto userDto);

    UserDto userToUserDto(User user);
}
