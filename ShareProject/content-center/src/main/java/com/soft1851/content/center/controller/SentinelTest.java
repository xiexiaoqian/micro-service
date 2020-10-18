package com.soft1851.content.center.controller;

import io.swagger.models.auth.In;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: maohe
 * @Description:
 * @Date: Created in 2020/10/6
 */

public class SentinelTest {
    public static void main(String[] args) throws InterruptedException {
        RestTemplate template = new RestTemplate();
        for (int i = 0; i<20; i++){
//            String o = template.getForObject("http://localhost:5001/notice/one", String.class);
            String o = template.getForObject("http://localhost:5001/test/byResource", String.class);
            System.out.println(o);
//            Thread.sleep(200);
        }
    }
}
