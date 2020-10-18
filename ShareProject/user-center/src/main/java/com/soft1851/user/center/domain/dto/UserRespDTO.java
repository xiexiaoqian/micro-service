package com.soft1851.user.center.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: maohe
 * @Description:
 * @Date: Created in 2020/10/13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRespDTO {

    private Integer id;

    private String avatarUrl;

    private Integer bonus;

    private String wxNickname;
}
