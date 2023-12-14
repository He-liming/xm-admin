package com.xm.module.sys.controller;

import com.xm.module.sys.entity.RoleEntity;
import com.xm.module.sys.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  控制类
 *
 * @author xiaoming
 * @date 2023-12-14
 */
@Tag(name = "角色管理")
@RestController
@RequestMapping("/sys/role")
public class RoleController {

    @Resource
    private RoleService service;

    @GetMapping("/page")
    @Operation(summary = "分页查询")
    public Page<RoleEntity> page(int page, int size) {
        return service.page(PageRequest.of(page - 1, size));
    }

    @GetMapping("/{id}")
    @Operation(summary = "根据用户ID查询")
    public RoleEntity get(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "根据用户名称查询")
    public RoleEntity getByName(@PathVariable String name) {
        return service.getByName(name);
    }

    @PostMapping
    @Operation(summary = "新增|修改")
    public void upsert(@RequestBody List<RoleEntity> users) {
        service.upsert(users);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "根据用户ID删除")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
