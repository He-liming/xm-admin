package com.xm.security;

import cn.hutool.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xm.common.response.Response;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

/**
 * 登录成功处理器
 *
 * @author xiaoming
 * @date 2023-12-17 20:58
 **/
@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private TokenProperties properties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 登录成功的时间
        Date now = new Date();
        // 认证成功返回 Token
        String token = JWT.create()
                // 用户
                .setPayload(JWT.SUBJECT, authentication.getName())
                // 签发时间
                .setPayload(JWT.ISSUED_AT, now)
                // 生效时间
                .setPayload(JWT.NOT_BEFORE, now)
                // 失效时间
                .setPayload(JWT.EXPIRES_AT, new Date(now.getTime() + properties.getExpire() * 1000))
                // 加密的key
                .setKey(properties.getKey().getBytes())
                .sign();
        response.setContentType("application/json;charset=UTF-8");
        String s = objectMapper.writeValueAsString(Response.ok(token));
        response.getWriter().println(s);
    }
}
