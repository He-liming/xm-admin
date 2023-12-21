package com.xm.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiaoming
 * @date 2023-12-18 23:45
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class TokenProperties {
    private String header;

    private Long expire;

    private String key;
}
