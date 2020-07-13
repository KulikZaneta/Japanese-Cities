package com.japan.demo.mapper;

import com.japan.demo.security.User;
import com.japan.demo.security.UserDto;

public interface UserMapper {
    User userDtoToUser(UserDto userDto);

    UserDto userToUserDto(User user);
}
