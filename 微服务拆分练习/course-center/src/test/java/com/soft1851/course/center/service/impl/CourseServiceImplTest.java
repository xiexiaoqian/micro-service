package com.soft1851.course.center.service.impl;

import com.soft1851.course.center.service.CourseService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseServiceImplTest {

    @Resource
    private CourseService courseService;

    @Test
    void getAll() {
        System.out.println(courseService.getAll());
    }
}