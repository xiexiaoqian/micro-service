package com.soft1851.user.center.domain.dto;

import com.soft1851.user.center.domain.entity.User;
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
public class LoginResDTO {
    private User user;
    private String token;
    private Integer isUserSignin;
}
