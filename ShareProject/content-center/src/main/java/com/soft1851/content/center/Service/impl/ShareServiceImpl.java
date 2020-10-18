package com.soft1851.content.center.Service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.soft1851.content.center.Service.ShareService;
import com.soft1851.content.center.dao.MidUserShareMapper;
import com.soft1851.content.center.dao.ShareMapper;
import com.soft1851.content.center.domain.dto.*;
import com.soft1851.content.center.domain.entity.MidUserShare;
import com.soft1851.content.center.domain.entity.Share;
import com.soft1851.content.center.feignclient.UserCenterFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
import tk.mybatis.mapper.util.StringUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @Author: maohe
 * @Description:
 * @Date: Created in 2020/9/29
 */

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShareServiceImpl implements ShareService {
    private final ShareMapper shareMapper;
    private final UserCenterFeignClient userCenterFeignClient;
    private final MidUserShareMapper midUserShareMapper;


    /**
     * 按发布人的id查找
     * @param id
     * @return
     */
    @Override
    public ShareDTO findById(Integer id) {
        // 获取分享实体
        Share share = this.shareMapper.selectByPrimaryKey(id);
        System.out.println(share);
        // 获得发布人id
        Integer userId = share.getUserId();

        // 1. 代码不可读
        // 2. 复杂的url难以维护
        // 3. 难以相应需求的变化，变化很没有幸福感
        // 4. 编程体验不统一
        //通过feign请求用户中心接口，获得发布人详情
        ResponseDTO responseDTO = this.userCenterFeignClient.findUserById(userId);
        UserDTO userDTO = convert(responseDTO);

        ShareDTO shareDTO = new ShareDTO();
        shareDTO.setShare(share);
        // 属性的装配
//        BeanUtils.copyProperties(share, shareDTO);

        shareDTO.setClientNickname(userDTO.getWxNickname());
        System.out.println(shareDTO);
        return shareDTO;
    }


    @Override
    public PageInfo<Share> query(String title, Integer pageNo, Integer pageSize, Integer userId) {
        // 启动分页
        PageHelper.startPage(pageNo, pageSize);
        // 构造查询实例
        Example example = new Example(Share.class);
        Example.Criteria criteria = example.createCriteria();
        // 如标题关键字不空，则加上模糊查询条件，否则结果即所有数据
        if (StringUtil.isNotEmpty(title)) {
            criteria.andLike("title", "%" + title + "%");
        }
        // 执行按条件查询
        List<Share> shares = shareMapper.selectByExample(example);
        // 处理后的 Share 数据列表
        List<Share> shareDeal;
        // 1. 如果用户未登录，那么 downloadURL 全部设为 null
        if (userId == null) {
            shareDeal = shares.stream()
                    .peek(share -> {
                        share.setDownloadUrl(null);
                    })
                    .collect(Collectors.toList());
        }
        // 2. 如果用户登录了，那么查询一下 mid_user_share，如果没有数据，那么这条 share 的download 也设为 null
        // 只有自己分享的资源才能直接看到下载链接， 否则显示“兑换”
        else {
            shareDeal = shares.stream()
                    .peek(share -> {
                        MidUserShare midUserShare = midUserShareMapper.selectOne(
                                MidUserShare.builder()
                                        .userId(userId)
                                        .shareId(share.getId())
                                        .build()
                        );
                        if (midUserShare == null) {
                            share.setDownloadUrl(null);
                        }
                    })
                    .collect(Collectors.toList());
        }
        return new PageInfo<>(shareDeal);
    }


    @Override
    public Share insertShare(ShareRequestDTO shareRequestDTO) {
        Share share = new Share();
        BeanUtils.copyProperties(shareRequestDTO, share);
        share.setUserId(1);
        share.setBuyCount(5);
        share.setCover("https://public-cdn-oss.mosoteach.cn/mssvc/cover/2020/09/7f41b776db0ef26d6bb4d92c61435bcc.jpg?x-oss-process=style/s200x200");
        share.setShowFlag("1");
        share.setAuditStatus("PASSED");
        share.setReason("通过");
        share.setCreateTime(LocalDateTime.now());
        share.setUpdateTime(LocalDateTime.now());
        shareMapper.insert(share);
        return share;
    }

    @Override
    public Share updateShareById(ShareRequestDTO shareRequestDTO, Integer id) {
        Share share = shareMapper.selectByPrimaryKey(id);
        BeanUtils.copyProperties(shareRequestDTO, share);
        shareMapper.updateByPrimaryKey(share);
        return share;
    }


    @Override
    public Share auditById(Integer id, AuditDTO auditDTO) {
        Share share = shareMapper.selectByPrimaryKey(id);
        BeanUtils.copyProperties(auditDTO, share);
        share.setAuditStatus(auditDTO.getAuditStatusEnum().toString());
        shareMapper.updateByPrimaryKeySelective(share);
        return share;
    }

    @Override
    public Share exchange(ExchangeDTO exchangeDTO){
        int userId = exchangeDTO.getUserId();
        int shareId = exchangeDTO.getShareId();
        // 1. 根据id查询share，校验是否存在
        Share share = this.shareMapper.selectByPrimaryKey(shareId);
        if (share == null) {
            throw new IllegalArgumentException("该分享不存在！");
        }
        Integer price = share.getPrice();

        // 2. 如果当前用户已经兑换过该分享，则直接返回
        MidUserShare midUserShare = this.midUserShareMapper.selectOne(
                MidUserShare.builder()
                        .shareId(shareId)
                        .userId(userId)
                        .build()
        );
        if (midUserShare != null) {
            return share;
        }

        // 3. 根据当前登录的用户id，查询积分是否够
        // !!! 通过调用户中心接口得到的返回值，外面已经封装了一层了，要解析才能拿到真正的用户数据 !!!
        ResponseDTO responseDTO = this.userCenterFeignClient.findUserById(userId);
        UserDTO userDTO = convert(responseDTO);
        System.out.println(userDTO);
        if (price > userDTO.getBonus()) {
            throw new IllegalArgumentException("用户积分不够！");
        }

        // 4. 扣积分
        this.userCenterFeignClient.addBonus(
                UserAddBonusMsgDTO.builder()
                        .userId(userId)
                        .bonus(price * -1)
                        .build()
        );
        //5. 向mid_user_share表里插入一条数据
        this.midUserShareMapper.insert(
                MidUserShare.builder()
                        .userId(userId)
                        .shareId(shareId)
                        .build()
        );
        return share;
    }

    /**
     * 将统一的返回响应结果转换为UserDTO类型
     * @param responseDTO
     * @return
     */
    private UserDTO convert(ResponseDTO responseDTO) {
        ObjectMapper mapper = new ObjectMapper();
        UserDTO userDTO = null;
        try {
            String json = mapper.writeValueAsString(responseDTO.getData());
            userDTO = mapper.readValue(json, UserDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return userDTO;
    }

    @Override
    public List<Share> myContributions(MyDTO myDTO) {
        Example example = new Example(Share.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", myDTO.getUserId());
        List<Share> myContributions = this.shareMapper.selectByExample(example);
        return myContributions;
    }

    @Override
    public List<Share> myExchange(MyDTO myDTO){
        // 查询中间表中，当前用户的拥有的资源
        Example example = new Example(MidUserShare.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId",myDTO.getUserId());
        List<MidUserShare> myExchange = this.midUserShareMapper.selectByExample(example);
        // 根据查到的中间表中的shareId，去到share表中，查询share详情
        List<Share> sharesDeal = new ArrayList<>();
        myExchange.forEach(item -> {
            Example example1 = new Example(Share.class);
            Criteria criteria1 = example1.createCriteria();
            criteria1.andEqualTo("id",item.getShareId());
            Share myExchangeInfo = this.shareMapper.selectOneByExample(example1);
            sharesDeal.add(myExchangeInfo);
        });
        return sharesDeal;
    }


    @Override
    public String getHello() {
        return this.userCenterFeignClient.getHello();
    }
}