package com.xm.module.sys.controller;

import com.xm.module.sys.entity.MenuEntity;
import com.xm.module.sys.service.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 控制类
 *
 * @author xiaoming
 * @date 2023-12-14
 */
@Tag(name = "菜单管理")
@RestController
@RequestMapping("/sys/menu")
public class MenuController {

    @Resource
    private MenuService service;

    @GetMapping("/page")
    @Operation(summary = "分页查询")
    public Page<MenuEntity> page(int page, int size) {
        return service.page(PageRequest.of(page - 1, size));
    }

    @GetMapping("/{id}")
    @Operation(summary = "根据用户ID查询")
    public MenuEntity get(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "根据用户名称查询")
    public MenuEntity getByName(@PathVariable String name) {
        return service.getByName(name);
    }

    @PostMapping
    @Operation(summary = "新增|修改")
    public void upsert(@RequestBody List<MenuEntity> users) {
        service.upsert(users);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "根据用户ID删除")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
