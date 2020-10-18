package com.soft1851.content.center.configuration;

import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Configuration;
import ribbonconfig.RibbonConfiguration;

/**
 * 给目标集群设置ribbon规则 的配置类
 */


@Configuration
//@RibbonClient(name = "user-center", configuration = RibbonConfiguration.class)
// 全局配置
@RibbonClients(defaultConfiguration = RibbonConfiguration.class)
public class UserCenterRibbonConfig {

}
