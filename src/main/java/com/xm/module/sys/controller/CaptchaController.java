package com.xm.module.sys.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import com.xm.common.constant.Constant;
import com.xm.common.util.RedisUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * 登录相关接口
 *
 * @author xiaoming
 * @date 2023-12-18 20:04
 **/
@Tag(name = "验证码管理")
@RestController
@RequestMapping("/sys/captcha")
public class CaptchaController {

    @Resource
    private RedisUtils redisUtils;

    @GetMapping("/line")
    @Operation(summary = "创建线段干扰的验证码")
    public void createLineCaptcha(HttpServletResponse response, HttpSession session) throws IOException {
        RandomGenerator randomGenerator = new RandomGenerator("0123456789", 4);
        LineCaptcha captcha = CaptchaUtil.createLineCaptcha(200, 100);
        captcha.setGenerator(randomGenerator);
        // 重新生成code
        captcha.createCode();
        // 验证码放到redis中并且30s过期
        redisUtils.set(Constant.CAPTCHA, captcha.getCode(), 30);
        // 直接写出到浏览器
        captcha.write(response.getOutputStream());
    }

    @GetMapping("/circle")
    @Operation(summary = "创建圆圈干扰验证码")
    public void createCircleCaptcha(HttpServletResponse response, HttpSession session) throws IOException {
        RandomGenerator randomGenerator = new RandomGenerator("0123456789", 4);
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(200, 100);
        captcha.setGenerator(randomGenerator);
        // 重新生成code
        captcha.createCode();
        // 验证码放到redis中并且30s过期
        redisUtils.set(Constant.CAPTCHA, captcha.getCode(), 30);
        // 直接写出到浏览器
        captcha.write(response.getOutputStream());
    }

    @GetMapping("/shear")
    @Operation(summary = "创建扭曲干扰验证码")
    public void createShearCaptcha(HttpServletResponse response, HttpSession session) throws IOException {
        RandomGenerator randomGenerator = new RandomGenerator("0123456789", 4);
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(200, 100);
        captcha.setGenerator(randomGenerator);
        // 重新生成code
        captcha.createCode();
        // 验证码放到redis中并且30s过期
        redisUtils.set(Constant.CAPTCHA, captcha.getCode(), 30);
        // 直接写出到浏览器
        captcha.write(response.getOutputStream());
    }

}
