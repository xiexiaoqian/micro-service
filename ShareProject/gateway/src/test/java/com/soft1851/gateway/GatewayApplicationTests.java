package com.soft1851.gateway;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZonedDateTime;

@SpringBootTest
class GatewayApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(ZonedDateTime.now());
    }

}
