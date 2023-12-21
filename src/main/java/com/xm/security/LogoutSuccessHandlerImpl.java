package com.xm.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xm.common.response.Response;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 退出登录处理器
 *
 * @author xiaoming
 * @date 2023-12-17 21:31
 **/
@Component
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    @Resource
    private ObjectMapper objectMapper;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        String s = objectMapper.writeValueAsString(Response.ok());
        response.getWriter().println(s);
    }
}
