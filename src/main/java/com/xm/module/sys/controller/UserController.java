package com.xm.module.sys.controller;

import com.xm.module.sys.entity.UserEntity;
import com.xm.module.sys.service.UserService;
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
@RestController
@RequestMapping("/sys/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/{id}")
    public UserEntity get(@PathVariable Long id) {
        return userService.get(id);
    }

    @GetMapping("/list")
    public List<UserEntity> lists() {
        return userService.lists();
    }

    @GetMapping("/page")
    public Page<UserEntity> page(int page, int size) {
        return userService.page(PageRequest.of(page - 1, size));
    }

    @PostMapping
    public void save(@RequestBody UserEntity user) {
        userService.save(user);
    }

    @PutMapping
    public void update(@RequestBody UserEntity user) {
        userService.update(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
