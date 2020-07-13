package com.japan.demo.security;

import com.japan.demo.mapper.UserMapper;
import com.japan.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;


    @PostMapping("/register")
    public void register(@RequestBody UserDto userDto) {
        userMapper.userToUserDto(userService.save(userMapper.userDtoToUser(userDto)));
    }
}
