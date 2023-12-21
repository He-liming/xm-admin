package com.xm.common.response;

/**
 * @author xiaoming
 * @date 2023-12-10 18:04
 **/
public enum ResponseCode {

    /**
     * 成功
     */
    OK(200, "OK"),
    /**
     * 未认证
     */
    UNAUTHORIZED(401, "未认证，请先登录！"),
    /**
     * 资源不存在
     */
    NOT_FOUND(404, "Not Found"),
    /**
     * 失败
     */
    ERROR(500, "系统异常，请稍后重试");

    private final int code;

    private final String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
