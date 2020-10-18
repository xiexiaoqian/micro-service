package com.soft1851.mosoteach.user.center.controller;

import com.soft1851.mosoteach.user.center.entity.User;
import com.soft1851.mosoteach.user.center.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Resource
    private UserMapper userMapper;

    /**
     * 获取用户列表中所有用户
     * @return
     */
    @GetMapping("/all")
    public List<User> getAll(){
        return userMapper.selectList(null);
    }

    /**
     * 通过用户id查找用户信息
     * @return
     */
    @PostMapping("/getById/{uId}")
    public User getUserById(@PathVariable int uId){
        return userMapper.selectById(uId);
    }

}
