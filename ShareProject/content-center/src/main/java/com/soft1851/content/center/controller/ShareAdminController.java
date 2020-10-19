package com.soft1851.content.center.controller;

import com.soft1851.content.center.Service.ShareService;
import com.soft1851.content.center.auth.CheckAuthorization;
import com.soft1851.content.center.domain.dto.AuditDTO;
import com.soft1851.content.center.domain.dto.UserAddBonusMsgDTO;
import com.soft1851.content.center.domain.entity.MidUserShare;
import com.soft1851.content.center.domain.entity.Share;
import com.soft1851.content.center.domain.enums.AuditStatusEnum;
import com.soft1851.content.center.utils.JwtOperator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: maohe
 * @Description:
 * @Date: Created in 2020/10/19
 */

@RestController
@RequestMapping("/admin/shares")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Api(tags = "管理员接口", value = "提供管理员相关的Rest API")
@Slf4j
public class ShareAdminController {

    private final ShareService shareService;


    @PutMapping(value = "/audit/{id}")
    @CheckAuthorization("admin")
    @ApiOperation(value = "管理员审核分享", notes = "管理员审核分享")
    public Share auditById(@PathVariable Integer id, @RequestBody AuditDTO auditDTO) {
        log.info(id + ">>>>>>>>>>>>>" + auditDTO);
        //此处需要认证授权
        return this.shareService.auditById(id, auditDTO);
    }

    @GetMapping(value = "/list")
    @CheckAuthorization("admin")
    @ApiOperation(value = "待审核分享列表", notes = "待审核分享列表")
    public List<Share> getSharesNotYet() {
        return shareService.querySharesNotYet();
    }
}
