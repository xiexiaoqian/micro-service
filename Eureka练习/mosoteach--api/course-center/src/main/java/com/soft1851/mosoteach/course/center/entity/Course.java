package com.soft1851.mosoteach.course.center.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("course_center")
public class Course {

    @TableField("id")
    private Integer id;

    @TableField("c_name")
    private String cName;

    @TableField("c_avatar")
    private String cAvatar;

    @TableField("c_flag")
    private Integer cFlag;

    @TableField("u_id")
    private Integer uId;

    @TableField("t_id")
    private Integer tId;

}
