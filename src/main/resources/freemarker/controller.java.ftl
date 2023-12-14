package ${package.Controller};

import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ${table.comment!} 控制类
 *
 * @author ${author}
 * @date ${date}
 */
@Tag(name = "${table.comment!}")
@RestController
@RequestMapping("<#if package.ModuleName?? && package.ModuleName != "">/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
public class ${table.controllerName} {

    @Resource
    private ${table.serviceName} service;

    @GetMapping("/page")
    @Operation(summary = "分页查询")
    public Page<${entity}> page(int page, int size) {
        return service.page(PageRequest.of(page - 1, size));
    }

    @GetMapping("/{id}")
    @Operation(summary = "根据用户ID查询")
    public ${entity} get(@PathVariable Long id) {
        return service.get(id);
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "根据用户名称查询")
    public ${entity} getByName(@PathVariable String name) {
        return service.getByName(name);
    }

    @PostMapping
    @Operation(summary = "新增|修改")
    public void upsert(@RequestBody List<${entity}> users) {
        service.upsert(users);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "根据用户ID删除")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
