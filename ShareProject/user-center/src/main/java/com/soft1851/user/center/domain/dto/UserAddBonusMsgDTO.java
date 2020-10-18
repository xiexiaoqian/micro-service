package com.soft1851.user.center.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;

/**
 * @Author: maohe
 * @Description:
 * @Date: Created in 2020/10/8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAddBonusMsgDTO {

    private Integer userId;

    private Integer bonus;
}
