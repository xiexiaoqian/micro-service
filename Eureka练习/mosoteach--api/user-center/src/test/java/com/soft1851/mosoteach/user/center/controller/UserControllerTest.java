package com.soft1851.mosoteach.user.center.controller;

import com.soft1851.mosoteach.user.center.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserControllerTest {
    @Resource
    private UserMapper userMapper;
    @Test
    public void geggh() {
        System.out.println(userMapper.selectById(2));
    }
}