package com.soft1851.user.center.service;

import com.soft1851.user.center.dao.UserMapper;
import com.soft1851.user.center.domain.dto.UserDto;
import com.soft1851.user.center.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    private final UserMapper userMapper;

    public UserDto getUserDto(int id){
        User user = userMapper.selectByPrimaryKey(id);
        UserDto userDto = new UserDto();
        userDto.setUName(user.getUName());
        userDto.setClassName(user.getClassName());
        return userDto;
    }
}
