package com.xm.common;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 接口文档配置类
 *
 * @author xiaoming
 * @date 2023-12-10 11:09
 **/
@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI().info(
                new Info().title("xm-admin 数据中台接口文档")
                        .version("1.0")
                        .description("Spring Boot3 + Spring Data Jpa + Spring Security 的后台项目接口文档")
                        .termsOfService("http://localhost:9527/")
                        .contact(new Contact().name("xiaoming").url("https://github.com/shixiaomingya"))
                        .license(new License().name("Apache 2.0").url("http://localhost:9527/")));
    }

}
