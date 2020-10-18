package com.soft1851.user.center.service;

import com.soft1851.user.center.domain.dto.LoginDTO;
import com.soft1851.user.center.domain.dto.ResponseDTO;
import com.soft1851.user.center.domain.dto.UserAddBonusMsgDTO;
import com.soft1851.user.center.domain.dto.UserSignInDTO;
import com.soft1851.user.center.domain.entity.BonusEventLog;
import com.soft1851.user.center.domain.entity.User;
import jdk.internal.dynalink.beans.BeansLinker;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.util.List;

/**
 * @Author: maohe
 * @Description:
 * @Date: Created in 2020/9/29
 */

@Service
public interface UserService {
    /**
     * 根据用户id查找用户详细信息
     * @param userId
     * @return
     */
    User findById(Integer userId);

    /**
     * 根据用户id，加积分
     * @param userAddBonusMsgDTO
     * @return
     */
    User addBonusById(UserAddBonusMsgDTO userAddBonusMsgDTO);

    /**
     * 用户登录
     * @param loginDTO
     * @param openId
     * @return
     */
    User login(LoginDTO loginDTO, String openId);

    /**
     * 用户签到
     * @param userSignInDTO
     * @return
     */
    ResponseDTO signIn(UserSignInDTO userSignInDTO);

    /**
     * 判断用户是否签到
     * @param userSignInDTO
     * @return
     */
    ResponseDTO checkIsSign(UserSignInDTO userSignInDTO);

    /**
     * 根据用户id查询积分明细
     * @param userSignInDTO
     * @return
     */
    List<BonusEventLog> bonusLog(UserSignInDTO userSignInDTO);

}
