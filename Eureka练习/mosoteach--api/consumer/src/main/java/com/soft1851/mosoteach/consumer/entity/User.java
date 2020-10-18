package com.soft1851.mosoteach.consumer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Integer id;

    private String uName;

    private String className;

    private String updateTime;

}
