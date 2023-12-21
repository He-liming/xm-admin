package com.xm.security.config;

import com.xm.security.AuthenticationEntryPointImpl;
import com.xm.security.AuthenticationFailureHandlerImpl;
import com.xm.security.AuthenticationSuccessHandlerImpl;
import com.xm.security.LogoutSuccessHandlerImpl;
import com.xm.security.filter.CaptchaFilter;
import com.xm.security.filter.TokenFilter;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring Security配置类
 *
 * @author xiaoming
 * @date 2023-12-16 19:40
 **/
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Resource
    private AuthenticationEntryPointImpl authenticationEntryPoint;

    @Resource
    private AuthenticationSuccessHandlerImpl authenticationSuccessHandler;

    @Resource
    private AuthenticationFailureHandlerImpl authenticationFailureHandler;

    @Resource
    private LogoutSuccessHandlerImpl logoutSuccessHandler;

    @Resource
    private CaptchaFilter captchaFilter;

    @Resource
    private TokenFilter tokenFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(authorize -> authorize
                        // 配置SpringDoc、Swagger、Knife4j不用登录也能访问
                        .requestMatchers(
                                "/swagger-ui.html/**",
                                "/swagger-resources/**",
                                "/webjars/**",
                                "/v3/**",
                                "/api",
                                "/api-docs/**",
                                "/doc.html/**",
                                "/api-docs").permitAll()
                        // druid 数据库监控不用认证
                        .requestMatchers("/druid/**").permitAll()
                        // 图片验证接口不需要认证
                        .requestMatchers("/sys/captcha/**").permitAll()
                        // 剩余所有接口都需要登录
                        .anyRequest().authenticated()
                )
                // 未认证处理器
                .exceptionHandling(handler -> handler
                        .authenticationEntryPoint(authenticationEntryPoint)
                )
                .formLogin(login -> login
                        // 指定表单登录的地址
                        .loginProcessingUrl("/login")
                        // 指定用户密码的参数字段名，默认是username和password，可以修改为实际表单中的字段名称
                        .usernameParameter("username")
                        .passwordParameter("password")
                        // 登录成功处理器
                        .successHandler(authenticationSuccessHandler)
                        // 登录失败处理器
                        .failureHandler(authenticationFailureHandler)
                        // 登录请求放行
                        .permitAll()
                )
                // 退出登录处理器
                .logout(logout -> logout
                        .logoutSuccessHandler(logoutSuccessHandler)
                )
                // 将验证码过滤器添加在认证过滤器之前
                .addFilterBefore(captchaFilter, UsernamePasswordAuthenticationFilter.class)
                // 将JWT添加在验证码过滤器之前，所以要放在下面
                .addFilterBefore(tokenFilter, CaptchaFilter.class)
                // 禁用CSRF
                .csrf(AbstractHttpConfigurer::disable)
                // 不创建 HttpSession
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .build();
    }

}