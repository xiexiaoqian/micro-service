package com.soft1851.content.center.controller;

import com.soft1851.content.center.Service.ShareService;
import com.soft1851.content.center.auth.CheckLogin;
import com.soft1851.content.center.domain.dto.*;
import com.soft1851.content.center.domain.entity.Share;
import com.soft1851.content.center.domain.enums.AuditStatusEnum;
import com.soft1851.content.center.feignclient.UserCenterFeignClient;
import com.soft1851.content.center.utils.JwtOperator;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: maohe
 * @Description:
 * @Date: Created in 2020/9/29
 */

@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(value = "/shares")
@Api(tags = "分享接口", value = "提供分享相关的Rest API")
public class ShareController {
    private final ShareService shareService;
    private final UserCenterFeignClient userCenterFeignClient;
    private final RocketMQTemplate rocketMQTemplate;
    private final JwtOperator jwtOperator;

    @GetMapping("/{shareId}")
    @ApiOperation(value = "查询指定id的分享详情", notes = "查询指定id的分享详情")
    public ShareDTO findById(@PathVariable Integer shareId) {
        return this.shareService.findById(shareId);
    }

    @GetMapping("/query")
    @ApiOperation(value = "分享列表", notes = "分享列表")
    public List<Share> query(
            @RequestParam(required = false) String title,
            @RequestParam(required = false, defaultValue = "1") Integer pageNo,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @RequestHeader(value = "X-Token", required = false) String token) {
        if (pageSize > 100) {
            pageSize = 100;
        }
        Integer userId = null;
        // 验证有无token
        if (!"no-token".equals(token)){
            System.out.println(token);
            Claims claims = this.jwtOperator.getClaimsFromToken(token);
            log.info(claims.toString());
            userId = (Integer) claims.get("id");
        }else{
            log.info("没有token");
        }
        return this.shareService.query(title, pageNo, pageSize, userId).getList();
    }

    @PostMapping("/contribute")
    @ApiOperation(value = "投稿", notes = "投稿")
    public Share contribute(@RequestBody ShareRequestDTO shareRequestDTO) {
        return shareService.insertShare(shareRequestDTO);
    }


    @PutMapping("/contribute/{shareId}")
    @ApiOperation(value = "编辑投稿", notes = "编辑指定id的投稿信息")
    public Share contributeById(@RequestBody ShareRequestDTO shareRequestDTO, @PathVariable Integer shareId) {
        return shareService.updateShareById(shareRequestDTO, shareId);
    }

    @PutMapping("/audit/{shareId}")
    @ApiOperation(value = "管理员审核状态", notes = "管理员审核状态")
    public Share auditById(@PathVariable Integer shareId, @RequestBody AuditDTO auditDTO) {
        Share share = shareService.auditById(shareId, auditDTO);
        // 通过rocketMQ发出消息，user-center自行捕捉后，调用加积分接口
        if (AuditStatusEnum.PASS.equals(auditDTO.getAuditStatusEnum())) {
            this.rocketMQTemplate.convertAndSend(
                    "add-bonus",
                    UserAddBonusMsgDTO.builder()
                            .userId(share.getUserId())
                            .bonus(50)
                            .build()
            );
        }
        // 通过feign直接调用 user-center 的加积分接口
//        userCenterFeignClient.bonus(
//                UserAddBonusMsgDTO.builder()
//                .userId(share.getUserId())
//                .bonus(10)
//                .build()
//        );
        return share;
    }

    @PostMapping("/exchange")
    @CheckLogin
    @ApiOperation(value = "兑换资源", notes = "用相应积分兑换资源")
    public Share exchange(@RequestBody ExchangeDTO exchangeDTO) {
        System.out.println(exchangeDTO + ">>>>>>>>>>>>");
        return this.shareService.exchange(exchangeDTO);
    }

    @GetMapping("/my/contributions")
    @ApiOperation(value = "我的投稿", notes = "用于：我的-我的投稿")
    public List<Share> myContributions(@RequestBody MyDTO myDTO){
        return this.shareService.myContributions(myDTO);
    }

    @GetMapping("/my/exchanges")
    @ApiOperation(value = "我的兑换", notes = "用于：我的-我的兑换")
    public List<Share> myExchange(@RequestBody MyDTO myDTO){
        return this.shareService.myExchange(myDTO);
    }



}
