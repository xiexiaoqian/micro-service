package com.soft1851.user.center.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.soft1851.user.center.domain.dto.*;
import com.soft1851.user.center.domain.entity.BonusEventLog;
import com.soft1851.user.center.domain.entity.User;
import com.soft1851.user.center.service.UserService;
import com.soft1851.user.center.utils.JwtOperator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserService userService;
    private final WxMaService wxMaService;
    private final JwtOperator jwtOperator;
//    private final ResponseDTO responseDTO;

    @GetMapping(value = "/{id}")
    public User findUserById(@PathVariable Integer id) {
        log.info("我被请求...");
        return this.userService.findById(id);
    }

    @PostMapping(value = "/login")
    public LoginRespDTO login(@RequestBody LoginDTO loginDTO) throws WxErrorException {
        System.out.println(loginDTO);
        String openId;
        // 微信小程序登录，需要根据code请求openId
        if (loginDTO.getLoginCode() != null) {
            // 微信服务端校验是否已经登录的结果
            WxMaJscode2SessionResult result = null;
            result = this.wxMaService.getUserService()
                    .getSessionInfo(loginDTO.getLoginCode());
            log.info(result.toString());
            // 微信的openId，用户在微信这边的唯一标识
            openId = result.getOpenid();
        } else {
            openId = loginDTO.getOpenId();
        }
        // 看用户是否注册，如果没有注册就（插入），如果已经注册就返回其信息
        User user = userService.login(loginDTO, openId);
        System.out.println(user);
        // 颁发token
        Map<String, Object> userInfo = new HashMap<>(3);
        userInfo.put("id", user.getId());
        userInfo.put("wxNickname", user.getWxNickname());
        userInfo.put("role", user.getRoles());
        String token = jwtOperator.generateToken(userInfo);

        log.info(
                "{}登录成功，生成的token = {}，有效期到：{}",
                user.getWxNickname(),
                token,
                jwtOperator.getExpirationTime()
        );

        // 构造返回结果
        return LoginRespDTO.builder()
                .user(
                        UserRespDTO.builder()
                                .id(user.getId())
                                .wxNickname(user.getWxNickname())
                                .avatarUrl(user.getAvatarUrl())
                                .bonus(user.getBonus())
                                .build()
                )
                .token(
                        JwtTokenRespDTO
                                .builder()
                                .token(token)
                                .expirationTime(jwtOperator.getExpirationTime().getTime())
                                .build()
                )
                .build();

    }

    @PostMapping(value = "/add-bonus")
    public void addBonus(@RequestBody UserAddBonusMsgDTO userAddBonusMsgDTO){
        userService.addBonusById(userAddBonusMsgDTO);
    }

    @GetMapping(value = "/bonus-logs")
    public List<BonusEventLog> bonusEventLogs (@RequestBody UserSignInDTO userSignInDTO){
        return userService.bonusLog(userSignInDTO);
    }

    @PostMapping(value = "/sign")
    public ResponseDTO signIn (@RequestBody UserSignInDTO userSignInDTO){
        return userService.signIn(userSignInDTO);
    }

}
