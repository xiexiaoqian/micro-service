package com.soft1851.mosoteach.consumer.vo;

import com.soft1851.mosoteach.consumer.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JoinCourseVo {

    private Integer id;

    private String cName;

    private String cAvatar;

    private Integer cFlag;

    private Integer uId;

    private Integer tId;


    private String tName;
}
