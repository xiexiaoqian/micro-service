package com.soft1851.content.center;

import com.purgeteam.dispose.starter.annotation.EnableGlobalDispose;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.spring.annotation.MapperScan;

//  一个异常处理库
@EnableGlobalDispose
@EnableFeignClients
@SpringBootApplication
@MapperScan("com.soft1851.content.center.dao")
public class ContentCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContentCenterApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
