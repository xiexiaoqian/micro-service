package com.soft1851.content.center.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@Slf4j
@RestController
@RequestMapping(value = "/content")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ContentController {
    private DiscoveryClient discoveryClient;
    private RestTemplate restTemplate;

    @GetMapping("/call")
    public String getHello(){
        return restTemplate.getForObject("http://lcoalhost:6001/user/hello", String.class);
    }
}
