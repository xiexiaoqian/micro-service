package com.soft1851.user.center.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "u_name")
    private String uName;

    @Column(name = "class_name")
    private String className;

    @Column(name = "update_time")
    private String updateTime;

}
