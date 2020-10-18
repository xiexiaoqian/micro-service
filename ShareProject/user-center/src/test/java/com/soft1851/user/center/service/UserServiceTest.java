package com.soft1851.user.center.service;

import com.soft1851.user.center.domain.entity.Event;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: maohe
 * @Description:
 * @Date: Created in 2020/9/29
 */

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void findById() {
        System.out.println(userService.findById(1));
    }

    @Test
    void test() {
        System.out.println(Event.EXCHANGE);
        System.out.println(Event.EXCHANGE.toString());
    }

}