package com.soft1851.course.center.controller;

import com.soft1851.course.center.domain.vo.CourseVo;
import com.soft1851.course.center.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/course")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CourseController {

    private final CourseService courseService;
    /**
     * 根据用户id查找加入的班课
     * @param uId
     * @return
     */
    @GetMapping("/listByUid/{uId}")
    public List<CourseVo> getListById(@PathVariable int uId) {
        return courseService.getCourseByUid(uId);
    }


}
