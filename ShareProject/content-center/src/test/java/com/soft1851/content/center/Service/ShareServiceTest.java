package com.soft1851.content.center.Service;

import com.github.pagehelper.PageInfo;
import com.soft1851.content.center.domain.entity.Share;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Author: maohe
 * @Description:
 * @Date: Created in 2020/9/29
 */

@SpringBootTest
class ShareServiceTest {

    @Autowired
    private ShareService shareService;

    @Test
    void findById() {
        System.out.println(shareService.findById(1));
    }

    @Test
    void query() {
        PageInfo<Share> query = shareService.query(null,1 , 10,1);
        query.getList().forEach(System.out::println);
    }
}