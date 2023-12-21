package com.xm.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xm.common.response.Response;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 登陆失败处理器
 *
 * @author xiaoming
 * @date 2023-12-17 21:19
 **/
@Component
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {


    @Resource
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        String s = objectMapper.writeValueAsString(Response.error(500, exception.getMessage()));
        response.getWriter().println(s);
    }
}
