package com.soft1851.content.center.domain.dto;

import com.soft1851.content.center.domain.enums.AuditStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: maohe
 * @Description:
 * @Date: Created in 2020/10/8
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ApiModel("分享审核DTO")
public class AuditDTO {
    /**
     * 审核状态
     */
    @ApiModelProperty(value = "auditStatusEnum", name = "审核状态")
    private AuditStatusEnum auditStatusEnum;

    /**
     * 原因
     */
    @ApiModelProperty(value = "reason", name = "原因")
    private String reason;

    /**
     * 是否发布显示
     */
    @ApiModelProperty(name = "showFlag",value = "是否发布显示")
    private Boolean showFlag;
}
