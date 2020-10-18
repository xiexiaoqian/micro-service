package com.soft1851.user.center.listener;

import com.soft1851.user.center.dao.BonusEventLogMapper;
import com.soft1851.user.center.dao.UserMapper;
import com.soft1851.user.center.domain.dto.UserAddBonusMsgDTO;
import com.soft1851.user.center.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: maohe
 * @Description:
 * @Date: Created in 2020/10/8
 */

@Service
//@RocketMQMessageListener(consumerGroup = "consumer", topic = "add-bonus")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AddBonusListener implements RocketMQListener<UserAddBonusMsgDTO> {
    private final UserService userService;

    @Override
    public void onMessage(UserAddBonusMsgDTO userAddBonusMsgDTO) {
        userService.addBonusById(userAddBonusMsgDTO);
    }
}
