package com.xm.common.handler;

import com.xm.common.response.Response;
import com.xm.common.response.ResponseCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常拦截
 *
 * @author xiaoming
 * @date 2023-12-10 19:08
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 全局异常处理。
     * ExceptionHandler(Exception.class) 表示拦截 Exception 异常，这是所有异常都拦截
     * 我们还可以配置 ExceptionHandler(XXException.class) 等等进行更加精细的拦截操作
     *
     * @param e 异常
     * @return Response
     */
    @ExceptionHandler(Exception.class)
    public Response<String> exception(Exception e) {
        return Response.error(ResponseCode.ERROR);
    }

}
