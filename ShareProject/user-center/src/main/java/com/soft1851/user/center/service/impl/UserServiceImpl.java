package com.soft1851.user.center.service.impl;

import com.soft1851.user.center.dao.BonusEventLogMapper;
import com.soft1851.user.center.dao.UserMapper;
import com.soft1851.user.center.domain.dto.LoginDTO;
import com.soft1851.user.center.domain.dto.ResponseDTO;
import com.soft1851.user.center.domain.dto.UserAddBonusMsgDTO;
import com.soft1851.user.center.domain.dto.UserSignInDTO;
import com.soft1851.user.center.domain.entity.BonusEventLog;
import com.soft1851.user.center.domain.entity.Event;
import com.soft1851.user.center.domain.entity.User;
import com.soft1851.user.center.service.UserService;
import com.soft1851.user.center.utils.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.Date;
import java.util.List;

/**
 * @Author: maohe
 * @Description:
 * @Date: Created in 2020/9/29
 */

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {


    private final UserMapper userMapper;
    private final BonusEventLogMapper bonusEventLogMapper;

    @Override
    public User findById(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public User addBonusById(UserAddBonusMsgDTO userAddBonusMsgDTO) {
        System.out.println(userAddBonusMsgDTO);
        // 为用户加积分
        Integer userId = userAddBonusMsgDTO.getUserId();
        User user = userMapper.selectByPrimaryKey(userId);
        user.setBonus(user.getBonus() + userAddBonusMsgDTO.getBonus());
        userMapper.updateByPrimaryKeySelective(user);

        // 判断是扣除积分还是加分
        if (userAddBonusMsgDTO.getBonus() > 0){
            // 写积分日志
            bonusEventLogMapper.insert(
                    BonusEventLog.builder()
                            .userId(userId)
                            .value(userAddBonusMsgDTO.getBonus())
                            .event(Event.CONTRIBUTE.name())
                            .createTime(new Date())
                            .description("投稿加分")
                            .build());
            return user;
        }else {
            // 写积分日志
            bonusEventLogMapper.insert(
                    BonusEventLog.builder()
                            .userId(userId)
                            .value(userAddBonusMsgDTO.getBonus())
                            .event(Event.EXCHANGE.name())
                            .createTime(new Date())
                            .description("兑换资源")
                            .build());
            return user;
        }

    }

    @Override
    public User login(LoginDTO loginDTO, String openId) {
        // 先根据openId查找用户
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("wxId", openId);
        List<User> users = this.userMapper.selectByExample(example);
        // 没找到，是新用户，直接注册
        if (users.size() == 0) {
            User saveUser = User.builder()
                    .wxId(openId)
                    .avatarUrl(loginDTO.getAvatarUrl())
                    .wxNickname(loginDTO.getWxNickname())
                    .roles("admin")
                    .bonus(100)
                    .createTime(new Date())
                    .updateTime(new Date())
                    .build();
            this.userMapper.insertSelective(saveUser);
            return saveUser;
        }
        return users.get(0);
    }

    @Override
    public ResponseDTO signIn(UserSignInDTO signInDTO) {
        User user = this.userMapper.selectByPrimaryKey(signInDTO.getUserId());
        if (user == null) {
            throw new IllegalArgumentException("该用户不存在！");
        }
        Example example = new Example(BonusEventLog.class);
        Example.Criteria criteria = example.createCriteria();
        example.setOrderByClause("id DESC");
        criteria.andEqualTo("userId", signInDTO.getUserId());
        criteria.andEqualTo("event", "SIGN_IN");
        List<BonusEventLog> bonusEventLog = this.bonusEventLogMapper.selectByExample(example);
        // 判断积分日志有没有该用户的记录，如果没有，则直接插入数据并提示签到成功
        if (bonusEventLog.size() == 0) {
            this.bonusEventLogMapper.insert(BonusEventLog.builder()
                    .userId(signInDTO.getUserId())
                    .event("SIGN_IN")
                    .value(20)
                    .description("签到加分")
                    .createTime(new Date())
                    .build());
            user.setBonus(user.getBonus() + 20);
            this.userMapper.updateByPrimaryKeySelective(user);
            return new ResponseDTO(true,"200","签到成功", user.getWxNickname() + "用户签到成功", 1l);
        }else {
            BonusEventLog bonusEventLog1 = bonusEventLog.get(0);
            Date date = bonusEventLog1.getCreateTime();
            try {
                // 调用工具类，今天没有签到，返回0
                if (DateUtil.checkAllotSigin(date) == 0){
                    // 给用户加积分，并将记录插入积分日志
                    this.bonusEventLogMapper.insert(BonusEventLog.builder()
                            .userId(signInDTO.getUserId())
                            .event("SIGN_IN")
                            .value(20)
                            .description("签到加积分")
                            .createTime(new Date())
                            .build());
                    user.setBonus(user.getBonus() + 20);
                    this.userMapper.updateByPrimaryKeySelective(user);
                    return new ResponseDTO(true, "200", "签到成功", user.getWxNickname() + "用户签到成功", 1l);
                }else if (DateUtil.checkAllotSigin(date) == 1){
                    return new ResponseDTO(true, "201", "签到失败", user.getWxNickname() + ",今天已经签过啦", 1l);

                }else if (DateUtil.checkAllotSigin(date) == 2){
                    return new ResponseDTO(true, "202", "签到失败", user.getWxNickname() + "服务器生病了", 1l);
                }
            } catch (Exception e){
                System.out.println("签到失败");
            }
            return new ResponseDTO(true,"200","签到成功", user.getWxNickname() + "用户签到成功", 1l);
        }
    }

    @Override
    public ResponseDTO checkIsSign(UserSignInDTO SignInDTO) {
        return null;
    }

    @Override
    public List<BonusEventLog> bonusLog(UserSignInDTO userSignInDTO) {
        Example example = new Example(BonusEventLog.class);
        example.setOrderByClause("create_time DESC");
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userSignInDTO.getUserId());
        List<BonusEventLog> bonusEventLogs = this.bonusEventLogMapper.selectByExample(example);
        return bonusEventLogs;
    }
}
