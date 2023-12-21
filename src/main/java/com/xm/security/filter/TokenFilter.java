package com.xm.security.filter;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.exceptions.ValidateException;
import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.JWTValidator;
import com.xm.security.AuthenticationFailureHandlerImpl;
import com.xm.security.TokenProperties;
import com.xm.security.exception.AuthException;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * jwt 过滤器
 *
 * @author xiaoming
 * @date 2023-12-18 23:35
 **/
@Component
public class TokenFilter extends OncePerRequestFilter {

    @Resource
    private TokenProperties properties;

    @Resource
    private UserDetailsService service;

    @Resource
    private AuthenticationFailureHandlerImpl authenticationFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(properties.getHeader());
        // 没有携带 Token 是需要放行的，没有携带 Token Spring Security 上下文中是没有用户信息的，在后面授权校验的时候会失败
        if (StrUtil.isEmpty(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            // 校验 token 是否是正确的 token
            boolean ok = JWTUtil.verify(token, properties.getKey().getBytes());
            if (!ok) {
                throw new AuthException("无效的Token！");
            }

            try {
                // 校验token是否超时
                JWTValidator.of(token).validateDate(DateUtil.date());
            } catch (ValidateException e) {
                throw new AuthException("Token已失效！");
            }

            // 获取用户名称
            String username = (String) JWTUtil.parseToken(token).getPayload(JWT.SUBJECT);

            // 获取用户权限等信息
            UserDetails user = service.loadUserByUsername(username);

            // 密码为null,是因为提供了正确的JWT,实现自动登录
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        } catch (AuthenticationException exception) {
            // 直接返回调用登录失败处理器
            authenticationFailureHandler.onAuthenticationFailure(request, response, exception);
            return;
        }

        filterChain.doFilter(request, response);
    }
}
