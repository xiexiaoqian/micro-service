package com.soft1851.mosoteach.course.center.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.soft1851.mosoteach.course.center.entity.Course;
import com.soft1851.mosoteach.course.center.mapper.CourseMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseControllerTest {
    @Resource
    private CourseMapper courseMapper;
    @Test
    void getListById() {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("u_id", 3);
        List<Course> courses = courseMapper.selectList(queryWrapper);
        Course course = courses.get(0);
        System.out.println(course);
    }
}