package com.xm.security.filter;

import cn.hutool.core.util.StrUtil;
import com.xm.common.constant.Constant;
import com.xm.security.AuthenticationFailureHandlerImpl;
import com.xm.security.exception.AuthException;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * 图片验证码过滤器
 *
 * @author xiaoming
 * @date 2023-12-18 20:02
 **/
@Component
public class CaptchaFilter extends OncePerRequestFilter {

    @Resource
    private AuthenticationFailureHandlerImpl authenticationFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // post 方式的 登录请求才需要验证, 否则放行
        if (!(Constant.LOGIN_PATH.equals(request.getServletPath()) && Constant.POST.equals(request.getMethod()))) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            // 获取参数验证码
            String captcha = request.getParameter(Constant.CAPTCHA);
            if (StrUtil.isEmpty(captcha)) {
                throw new AuthException("验证码为空！");
            }

            // 获取存储的验证码
            String sessionCaptcha = (String) request.getSession().getAttribute(Constant.CAPTCHA);
            if (!captcha.equalsIgnoreCase(sessionCaptcha)) {
                throw new AuthException("验证码错误！");
            }
        } catch (AuthenticationException exception) {
            // 直接返回调用登录失败处理器
            authenticationFailureHandler.onAuthenticationFailure(request, response, exception);
            return;
        }

        // 校验完毕继续往下走（登录认证过滤器）
        filterChain.doFilter(request, response);
    }
}
