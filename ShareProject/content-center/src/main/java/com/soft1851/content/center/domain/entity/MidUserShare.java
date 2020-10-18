package com.soft1851.content.center.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author: maohe
 * @Description:
 * @Date: Created in 2020/10/4
 */

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
@Table(name = "mid_user_share")
public class MidUserShare {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "share_id")
    private Integer shareId;

    @Column(name = "user_id")
    private Integer userId;
}
