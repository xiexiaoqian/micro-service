package com.soft1851.content.center.feignclient;

import com.soft1851.content.center.domain.dto.ResponseDTO;
import com.soft1851.content.center.domain.dto.UserAddBonusMsgDTO;
import com.soft1851.content.center.domain.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author: maohe
 * @Description:
 * @Date: Created in 2020/9/29
 */

@FeignClient(name = "user-center")
public interface UserCenterFeignClient {


    /**
     * http://user-center/users/{id}
     * @param id
     * @return
     */
    @GetMapping("/users/{id}")
    ResponseDTO findUserById(@PathVariable Integer id);


    /**
     * 为用户加积分并写积分日志
     * @param userAddBonusMsgDTO
     */
    @PostMapping("/users/add-bonus")
    void addBonus(@RequestBody UserAddBonusMsgDTO userAddBonusMsgDTO);

    /**
     * hello测试
     * @return
     */
    @GetMapping("/users/hello")
    String getHello();



}
