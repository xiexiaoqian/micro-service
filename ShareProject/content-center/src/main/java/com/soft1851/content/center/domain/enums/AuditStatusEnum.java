package com.soft1851.content.center.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: maohe
 * @Description:
 * @Date: Created in 2020/9/29
 */

@Getter
@AllArgsConstructor
public enum  AuditStatusEnum {

    /**
     * 待审核
     */
    NOT_YET,

    /**
     * 审核通过
     */
    PASS,

    /**
     * 审核不通过
     */
    REJECT
}
