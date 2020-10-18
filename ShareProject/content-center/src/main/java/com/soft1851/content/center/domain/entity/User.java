package com.soft1851.content.center.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
//import java.util.Date;

/**
 * @Author: maohe
 * @Description:
 * @Date: Created in 2020/9/29
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "client_id")
    private String wxId;

    @Column(name = "client_nickname")
    private String wxNickname;

    @Column(name = "roles")
    private String roles;

    @Column(name = "avatar_url")
    private String avatarUrl;

//    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
//    @Column(name = "create_time")
//    private Date createTime;
//
//    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
//    @Column(name = "update_time")
//    private Date updateTime;

    @Column(name = "bonus")
    private String bonus;



}
