package com.soft1851.user.center.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: maohe
 * @Description:
 * @Date: Created in 2020/10/12
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
    private String openId;
    private String loginCode;
    private String wxNickname;
    private String avatarUrl;
}
