package com.soft1851.user.center.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @Author: maohe
 * @Description:
 * @Date: Created in 2020/10/8
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "bonus_event_log")
public class BonusEventLog {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "value")
    private Integer value;

    @Column(name = "event")
    private String event;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "description")
    private String description;
}
