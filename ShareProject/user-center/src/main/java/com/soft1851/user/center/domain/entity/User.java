package com.soft1851.user.center.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Author: maohe
 * @Description:
 * @Date: Created in 2020/9/29
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "wx_id")
    private String wxId;

    @Column(name = "wx_nickname")
    private String wxNickname;

    @Column(name = "roles")
    private String roles;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "bonus")
    private Integer bonus;



}
