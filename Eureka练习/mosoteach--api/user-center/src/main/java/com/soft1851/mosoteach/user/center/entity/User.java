package com.soft1851.mosoteach.user.center.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user_center")
public class User {

    @TableField("id")
    private Integer id;

    @TableField("u_name")
    private String uName;

    @TableField("class_name")
    private String className;

    @TableField("update_time")
    private String updateTime;

}
