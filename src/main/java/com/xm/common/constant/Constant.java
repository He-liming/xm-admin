package com.xm.common.constant;

import org.springframework.http.HttpMethod;

/**
 * Spring security常量类
 *
 * @author xiaoming
 * @date 2023-12-18 23:19
 **/
public class Constant {


    public static final String POST = HttpMethod.POST.name();

    public static final String LOGIN_PATH = "/login";

    public static final String CAPTCHA = "captcha";
}
