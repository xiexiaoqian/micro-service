package com.soft1851.course.center.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.soft1851.course.center.domain.entity.Course;
import com.soft1851.course.center.domain.userDto.UserDto;
import com.soft1851.course.center.domain.vo.CourseVo;
import com.soft1851.course.center.mapper.CourseMapper;
import com.soft1851.course.center.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CourseServiceImpl implements CourseService {

    private final CourseMapper courseMapper;

    private final RestTemplate restTemplate;

//    @Override
//    public List<CourseVo> getCourseByUid(Integer uId) {
//        List<Course> listByUid = courseMapper.getListByUid(uId);
//        List<CourseVo> courseVoList = new ArrayList<>();
//        for (Course course : listByUid) {
//            Integer tId = course.getTId();
//            UserDto forObject = restTemplate.getForObject("http://localhost:8001/user/" + tId, UserDto.class);
//            CourseVo courseVo = CourseVo.builder()
//                    .id(course.getId())
//                    .cName(course.getCName())
//                    .cAvatar(course.getCAvatar())
//                    .cFlag(course.getCFlag())
//                    .uId(course.getUId())
//                    .tName(forObject.getUName())
//                    .clazzName(forObject.getClassName())
//                    .build();
//            courseVoList.add(courseVo);
//        }
//
//        return courseVoList;
//    }

    @Override
    public List<CourseVo> getCourseByUid(Integer uId) {
        List<Course> listByUid = courseMapper.getListByUid(uId);
        List<CourseVo> courseVoList = new ArrayList<>();
        for (Course course : listByUid) {
            Integer tId = course.getTId();
            String jsonDto = restTemplate.getForObject("http://127.0.0.1:5000/user/" + tId, String.class);
            JSONObject jsonObject = JSONObject.parseObject(jsonDto);
            UserDto userDto = JSON.toJavaObject(jsonObject, UserDto.class);
            CourseVo courseVo = CourseVo.builder()
                    .id(course.getId())
                    .cName(course.getCName())
                    .cAvatar(course.getCAvatar())
                    .cFlag(course.getCFlag())
                    .uId(course.getUId())
                    .tName(userDto.getUName())
                    .clazzName(userDto.getClassName())
                    .build();
            courseVoList.add(courseVo);
        }

        return courseVoList;
    }

    @Override
    public List<Course> getAll() {
        return courseMapper.getAll();
    }
}
