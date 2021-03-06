package com.japan.demo.controller;

import com.japan.demo.mapper.UserMapper;
import com.japan.demo.model.User;
import com.japan.demo.model.dto.UserDto;
import com.japan.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public Page<UserDto> getUserPage(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> userPage = userService.getPage(pageable);
        return userPage.map(userMapper::userToUserDto);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/current")
    public UserDto infoUser() {
        return userMapper.userToUserDto(userService.findByCurrentUser());
    }
}
