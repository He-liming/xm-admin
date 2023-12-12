package com.xm.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * 配置jpa自动填充用户
 *
 * @author xiaoming
 * @date 2023-12-09 13:56
 **/
@Configuration
public class JpaAuditorConfig implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        // TODO 先写死，等集成了Spring Security后再获取实际用户
        return Optional.of("admin");
    }
}
