package com.soft1851.course.center.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    private Integer id;

    private String cName;

    private String cAvatar;

    private Integer cFlag;

    private Integer uId;

    private Integer tId;

}
