package com.soft1851.content.center.Service;

import com.soft1851.content.center.domain.entity.Notice;

/**
 * @Author: maohe
 * @Description:
 * @Date: Created in 2020/10/4
 */

public interface NoticeService {

    /**
     * 查询最新公告
     * @return
     */
    Notice getLatest();
}
