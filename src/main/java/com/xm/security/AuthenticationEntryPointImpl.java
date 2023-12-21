package com.xm.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xm.common.response.Response;
import com.xm.common.response.ResponseCode;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 认证失败处理器
 *
 * @author xiaoming
 * @date 2023-12-17 18:55
 **/
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Resource
    private ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        // 拼装成json格式返回前端
        String s = objectMapper.writeValueAsString(Response.error(ResponseCode.UNAUTHORIZED));
        response.getWriter().println(s);
    }
}
