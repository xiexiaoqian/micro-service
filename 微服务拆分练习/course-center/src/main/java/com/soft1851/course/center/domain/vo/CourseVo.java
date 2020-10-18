package com.soft1851.course.center.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseVo {

    private Integer id;

    //    课程名称
    private String cName;

    private String cAvatar;

    private Integer cFlag;

    private Integer uId;

    //    班级名称
    private String clazzName;

    private String tName;
}
