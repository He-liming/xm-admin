package com.xm.module.sys.controller;

import com.xm.module.sys.entity.UserEntity;
import com.xm.module.sys.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户管理
 *
 * @author xiaoming
 * @date 2023-12-09 12:36
 **/
@Tag(name = "用户管理")
@RestController
@RequestMapping("/sys/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/{id}")
    @Operation(summary = "根据用户ID查询用户")
    public UserEntity get(@PathVariable Long id) {
        return userService.get(id);
    }

    @GetMapping("/list")
    @Operation(summary = "查询全部")
    public List<UserEntity> lists() {
        return userService.lists();
    }

    @GetMapping("/page")
    @Operation(summary = "分页查询")
    public Page<UserEntity> page(int page, int size) {
        return userService.page(PageRequest.of(page - 1, size));
    }

    @PostMapping
    @Operation(summary = "新增用户")
    public void save(@RequestBody UserEntity user) {
        userService.save(user);
    }

    @PutMapping
    @Operation(summary = "修改用户")
    public void update(@RequestBody UserEntity user) {
        userService.update(user);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "根据用户ID删除用户")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
