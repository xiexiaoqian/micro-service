package com.soft1851.user.center.controller;


import com.soft1851.user.center.domain.dto.UserDto;
import com.soft1851.user.center.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
     private final UserService userService;

    /**
     * 通过用户id查找用户信息
     * @return
     */
    @GetMapping("/{uId}")
    public UserDto getUserDto(@PathVariable int uId){
        return userService.getUserDto(uId);
    }

}
