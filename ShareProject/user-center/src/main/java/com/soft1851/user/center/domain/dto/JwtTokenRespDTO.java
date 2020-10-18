package com.soft1851.user.center.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: maohe
 * @Description: 返回结果中的Jwt数据对象
 * @Date: Created in 2020/10/13
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtTokenRespDTO {
    private String token;

    private Long expirationTime;
}
