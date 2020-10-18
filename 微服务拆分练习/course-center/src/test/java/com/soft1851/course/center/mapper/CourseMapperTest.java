package com.soft1851.course.center.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseMapperTest {

    @Resource
    private CourseMapper courseMapper;

    @Test
    void get(){
        System.out.println(courseMapper.getListByUid(3));
    }

}