package com.soft1851.content.center.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.soft1851.content.center.Service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(value = "/test")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TestController {
    private final DiscoveryClient discoveryClient;
    private final RestTemplate restTemplate;
    private final TestService testService;

    /**
     * 测试服务发现，证明内容中心总能找到用户中心
     *
     * @return
     */
    @GetMapping("/discovery")
    public List<ServiceInstance> getInstances() {
        return this.discoveryClient.getInstances("user-center");
    }

    @GetMapping("/call/hello")
    public String callUserCenter() {
        // 用户中心所有的实例信息
        List<ServiceInstance> instances = discoveryClient.getInstances("user-center");
        //stream编程、Lambda表达式、函数式编程
//        String targetUrl = instances.stream()
//                .map(instance -> instance.getUri().toString() + "/user/hello")
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException("当前没有实例！"));

        // 所有实例的url集合
        List<String> targetUrls = instances.stream()
                .map(instance -> instance.getUri().toString() + "/user/hello")
                .collect(Collectors.toList());
        // 随机数
        int i = ThreadLocalRandom.current().nextInt(targetUrls.size());

        log.info("请求的目标地址：{}", targetUrls.get(i));
        return restTemplate.getForObject(targetUrls.get(i), String.class);
    }

    /**
     * 负载均衡接口
     * @return
     */
    @GetMapping(value = "/call/ribbon")
    public String callByRibbon(){
        return restTemplate.getForObject("http://user-center/user/hello",String.class);
    }


//    @GetMapping("/test-a")
//    public String testA(){
//        this.testService.commonMethod();
//        return "test-a";
//    }
//
//    @GetMapping("/test-b")
//    public String testB(){
//        this.testService.commonMethod();
//        return "test-b";
//    }

    @GetMapping("/byResource")
    @SentinelResource(value = "byResource", blockHandler = "handleException")
    public String byResource(){
        return "按名称限流";
    }

    public String handleException(BlockException blockException){
        return "服务不可用";
    }

}
