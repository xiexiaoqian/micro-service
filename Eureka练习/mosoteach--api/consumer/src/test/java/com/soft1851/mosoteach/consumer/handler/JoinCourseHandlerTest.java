package com.soft1851.mosoteach.consumer.handler;

import com.alibaba.fastjson.JSON;
import com.soft1851.mosoteach.consumer.entity.Course;
import com.soft1851.mosoteach.consumer.vo.JoinCourseVo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JoinCourseHandlerTest {
    @Resource
    private RestTemplate restTemplate;
    @Test
    void getCListById() {
        List list = restTemplate.getForObject("http://xxq.com:8002/api/course/byuid/" + 3, List.class);
        String listJson = JSON.toJSONString(list);
        List<JoinCourseVo> courseList = JSON.parseArray(listJson, JoinCourseVo.class);
        System.out.println(courseList.get(0));
    }
}