package com.soft1851.content.center.Service.impl;

import com.soft1851.content.center.Service.NoticeService;
import com.soft1851.content.center.dao.NoticeMapper;
import com.soft1851.content.center.domain.entity.Notice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * @Author: maohe
 * @Description:
 * @Date: Created in 2020/10/4
 */

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NoticeServiceImpl implements NoticeService {
    private final NoticeMapper noticeMapper;

    @Override
    public Notice getLatest() {
        Example example = new Example(Notice.class);
        //按id顺序
        example.setOrderByClause("id DESC");
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("showFlag", 1);
        return noticeMapper.selectByExample(example).get(0);
    }
}
