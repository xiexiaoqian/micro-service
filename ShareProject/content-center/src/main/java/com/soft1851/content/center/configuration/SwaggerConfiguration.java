package com.soft1851.content.center.configuration;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * @Author: maohe
 * @Description:
 * @Date: Created in 2020/10/4
 */

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "share-app Swagger文档",
                "github地址：https://github.com/xiexiaoqian/micro-service",
                "API V1.0",
                "Terms of service",
                new Contact("谢晓茜", "https://xiexiaoqian.cn", "2320832818@qq.com"),
                "Apache", "http://www.apache.org/", Collections.emptyList());
    }

    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.soft1851.content.center"))
                .build()
                .apiInfo(apiInfo());
    }
}
