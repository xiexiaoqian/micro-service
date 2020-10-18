package com.soft1851.content.center.controller;

import com.soft1851.content.center.Service.NoticeService;
import com.soft1851.content.center.domain.entity.Notice;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: maohe
 * @Description:
 * @Date: Created in 2020/10/4
 */

@RestController
@RequestMapping(value = "/notices")
@Api(tags = "公告接口", value = "提供公告相关的Rest API")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NoticeController {
    private final NoticeService noticeService;

    @GetMapping(value = "/latest")
    @ApiOperation(value = "查询最新一条公告", notes = "查询最新一天公告")
    public Notice getTopNotice(){
        return this.noticeService.getLatest();
    }
}
