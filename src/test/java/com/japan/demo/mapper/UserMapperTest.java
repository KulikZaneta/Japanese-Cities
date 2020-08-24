package com.japan.demo.mapper;

import com.japan.demo.mapper.impl.UserMapperImp;
import com.japan.demo.model.User;
import com.japan.demo.model.dto.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapperImp userMapper;

    @Test
    public void mapToUserDtoTest() {
        //given
        User user = new User(1L, "test username", "test password", new HashSet<>(), "test email");

        //when
        UserDto userDto = userMapper.userToUserDto(user);

        //then
        assertEquals(user.getId(), userDto.getId());
        assertEquals(user.getUsername(), userDto.getUsername());
        assertEquals(user.getPassword(), userDto.getPassword());
        assertEquals(user.getRoles(), userDto.getRoles());
        assertEquals(user.getEmail(), userDto.getEmail());
    }

    @Test
    public void mapToUserTest() {
        //given
        UserDto userDto = new UserDto(1L, "test username", "test password", new HashSet<>(), "test email");

        //when
        User user = userMapper.userDtoToUser(userDto);

        //then
        assertEquals(userDto.getId(), user.getId());
        assertEquals(userDto.getUsername(), user.getUsername());
        assertEquals(userDto.getPassword(), user.getPassword());
        assertEquals(userDto.getEmail(), user.getEmail());
    }

}
