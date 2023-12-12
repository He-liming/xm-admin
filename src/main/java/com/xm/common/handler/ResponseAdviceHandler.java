package com.xm.common.handler;

import cn.hutool.json.JSONUtil;
import com.xm.common.response.Response;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 统一响应拦截类
 *
 * @author xiaoming
 * @date 2023-12-10 18:31
 **/
@RestControllerAdvice(basePackages = {"com.xm.module"})
public class ResponseAdviceHandler implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // 响应都需要被拦截
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof String) {
            // String 类型不能封装成 Response 返回
            return JSONUtil.toJsonStr(Response.ok(body));
        } else if (body instanceof Response) {
            // 如果是 Response 就直接返回了
            return body;
        } else {
            // 统一响应对象
            return Response.ok(body);
        }
    }
}
