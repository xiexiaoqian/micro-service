package com.soft1851.content.center.Service;

import com.github.pagehelper.PageInfo;
import com.soft1851.content.center.domain.dto.*;
import com.soft1851.content.center.domain.entity.Share;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * @Author: maohe
 * @Description:
 * @Date: Created in 2020/9/29
 */


public interface ShareService {
    /**
     * 获得分享详情
     *
     * @return ShareDTO
     */
    ShareDTO findById(Integer id);

    /**
     * 根据标题模糊查询某个用户的分享列表数据，title为空则为所有数据，查询结果分页
     *
     * @param title
     * @param pageNo
     * @param pageSize
     * @param userId
     * @return
     */
    PageInfo<Share> query(String title, Integer pageNo, Integer pageSize, Integer userId);

    /**
     * 发布一篇文章
     *
     * @param shareRequestDTO
     * @return
     */
    int contribute(ShareRequestDTO shareRequestDTO);

    /**
     * 编辑投稿
     *
     * @param shareRequestDTO
     * @param id
     * @return
     */
    Share updateShareById(ShareRequestDTO shareRequestDTO, Integer id);


    /**
     * 审核指定内容
     *
     * @param id
     * @return
     */
    Share auditById(Integer id, AuditDTO auditDTO);

    /**
     * 兑换资源
     *
     * @param exchangeDTO
     * @return
     */
    Share exchange(ExchangeDTO exchangeDTO);

    /**
     * 我的投稿
     */
    List<Share> myContributions(MyDTO myDTO);

    /**
     * 我兑换的资源
     * @param myDTO
     * @return
     */
    List<Share> myExchange(MyDTO myDTO);


    String getHello();
}
