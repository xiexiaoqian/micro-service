package com.soft1851.course.center.service;

import com.soft1851.course.center.domain.entity.Course;
import com.soft1851.course.center.domain.vo.CourseVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {

    List<CourseVo> getCourseByUid(Integer uId);

    List<Course> getAll();
}
