package com.xm.common.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.lang.Nullable;

import java.io.Serializable;

/**
 * 统一响应结果
 *
 * @author xiaoming
 * @date 2023-12-10 17:53
 **/
@Data
public class Response<T> implements Serializable {

    @Schema(description = "响应状态")
    private Integer code;

    @Schema(description = "响应信息")
    private String message;

    @Schema(description = "响应数据")
    private T body;

    /**
     * 私有化构造函数
     */
    private Response() {
    }

    /**
     * 构建 Response<T> 对象
     *
     * @param body 响应数据
     * @param code 响应状态对象
     */
    public static <T> Response<T> build(@Nullable T body, ResponseCode code) {
        return build(body, code.getCode(), code.getMessage());
    }

    /**
     * 构建 Response<T> 对象
     *
     * @param body    响应数据
     * @param code    响应状态
     * @param message 响应信息
     */
    public static <T> Response<T> build(@Nullable T body, Integer code, String message) {
        Response<T> response = new Response<>();
        response.setCode(code);
        response.setMessage(message);
        response.setBody(body);
        return response;
    }

    /**
     * 请求成功返回不带数据
     */
    public static <T> Response<T> ok() {
        return build(null, ResponseCode.OK);
    }

    /**
     * 请求成功返回带数据
     */
    public static <T> Response<T> ok(T body) {
        return build(body, ResponseCode.OK);
    }

    /**
     * 请求失败返回500
     */
    public static <T> Response<T> error() {
        return build(null, ResponseCode.ERROR);
    }

    /**
     * 请求失败状态码
     */
    public static <T> Response<T> error(ResponseCode code) {
        return build(null, code);
    }

    /**
     * 自定义请求失败信息
     */
    public static <T> Response<T> error(Integer code, String message) {
        return build(null, code, message);
    }

}
