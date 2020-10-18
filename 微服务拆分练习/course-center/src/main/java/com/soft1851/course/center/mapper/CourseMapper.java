package com.soft1851.course.center.mapper;

import com.soft1851.course.center.domain.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CourseMapper {
    @Select("SELECT * FROM t_course WHERE u_id = #{uId}")
    List<Course> getListByUid(Integer uId);

    @Select("SELECT * FROM t_course")
    List<Course> getAll();
}
