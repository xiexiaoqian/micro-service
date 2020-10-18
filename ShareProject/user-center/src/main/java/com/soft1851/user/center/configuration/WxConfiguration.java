package com.soft1851.user.center.configuration;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author su
 * @className WxConfiguration
 * @Description TODO
 * @Date 2020/10/13
 * @Version 1.0
 **/
@Configuration
public class WxConfiguration {
    @Bean
    public WxMaConfig wxMaConfig(){
        WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl();
        config.setAppid("wx8d3934fda053a01f");
        config.setSecret("6ea3822b702bc9ac688856c4330b89e6");
        return config;
    }

    @Bean
    public WxMaService wxMaService(WxMaConfig wxMaConfig){
        WxMaServiceImpl service = new WxMaServiceImpl();
        service.setWxMaConfig(wxMaConfig);
        return service;
    }
}
