package com.soft1851.content.center.feignclient;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: maohe
 * @Description:
 * @Date: Created in 2020/10/13
 */

public class TokenInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        // 获取到token
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = attributes.getRequest();
        String token = request.getHeader("X-token");

        // 将token传递
        if (StringUtils.isNotBlank(token)){
            template.header("X-token", token);
        }

    }
}
