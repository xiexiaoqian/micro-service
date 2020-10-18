package com.soft1851.content.center.Service.impl;

import com.github.pagehelper.PageInfo;
import com.soft1851.content.center.Service.ShareService;
import com.soft1851.content.center.domain.dto.AuditDTO;
import com.soft1851.content.center.domain.dto.MyDTO;
import com.soft1851.content.center.domain.dto.ShareRequestDTO;
import com.soft1851.content.center.domain.entity.Share;
import com.soft1851.content.center.domain.enums.AuditStatusEnum;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: maohe
 * @Description:
 * @Date: Created in 2020/10/4
 */

@SpringBootTest
class ShareServiceImplTest {

    @Autowired
    private ShareService shareService;

    @Test
    void query(){
        PageInfo<Share> query = shareService.query(null, 1, 10, 1);
        List<Share> list = query.getList();
        list.forEach(item -> System.out.println(item.getTitle() + "," +item.getDownloadUrl()));
    }

    @Test
    void insertShare() {
        Share share = shareService.insertShare(new ShareRequestDTO("张张", "urlnew",
                true, 23, "保姆级教学篇1", "Meaven报错怎么办？"));
        System.out.println(share);

    }

    @Test
    void updateShareById() {
        ShareRequestDTO shareRequestDTO = new ShareRequestDTO("巴旦木", "urlnew",
                true, 23, "世间美好", "与你环环相扣");
        Share share = shareService.updateShareById(shareRequestDTO, 1);
        System.out.println(share);
    }

    @Test
    void auditById() {
        System.out.println(shareService.auditById(7, new AuditDTO(AuditStatusEnum.REJECT, "不通过")));
    }


    @Test
    void myContributions() {
        List<Share> myContributions = shareService.myContributions(new MyDTO(1));
        myContributions.forEach(System.out::println);
    }

    @Test
    void myExchange() {
        List<Share> myExchanges = shareService.myExchange(new MyDTO(9));
        myExchanges.forEach(System.out::println);
    }
}