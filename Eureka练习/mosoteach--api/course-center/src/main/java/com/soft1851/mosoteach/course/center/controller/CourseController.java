package com.soft1851.mosoteach.course.center.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.soft1851.mosoteach.course.center.entity.Course;
import com.soft1851.mosoteach.course.center.mapper.CourseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/course")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CourseController {

    private final CourseMapper courseMapper;

    /**
     * 获取课程列表中所有课程
     *
     * @return
     */
    @GetMapping("/all")
    public List<Course> getAll() {
        return courseMapper.selectList(null);
    }


    /**
     * 根据用户id查找加入的班课
     * @param uid
     * @return
     */
    @GetMapping("/byuid/{uid}")
    public List<Course> getListById(@PathVariable int uid) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("u_id", uid);
        return courseMapper.selectList(queryWrapper);
    }

}
