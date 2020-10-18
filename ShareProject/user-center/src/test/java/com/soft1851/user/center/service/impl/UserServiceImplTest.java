package com.soft1851.user.center.service.impl;

import com.soft1851.user.center.domain.dto.UserAddBonusMsgDTO;
import com.soft1851.user.center.domain.dto.UserSignInDTO;
import com.soft1851.user.center.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: maohe
 * @Description:
 * @Date: Created in 2020/10/8
 */

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    void addBonusById() {
        System.out.println(userService.addBonusById(new UserAddBonusMsgDTO(1, 51)));
    }

    @Test
    void signIn() {
        System.out.println(userService.signIn(new UserSignInDTO(9)));
    }
}