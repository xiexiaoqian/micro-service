package com.soft1851.content.center.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @Author: maohe
 * @Description:
 * @Date: Created in 2020/10/15
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDTO {
    private Boolean succ;
    private String code;
    private String msg;
    private Object data;
    private Long ts;
}