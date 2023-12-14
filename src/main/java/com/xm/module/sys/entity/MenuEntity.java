package com.xm.module.sys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xm.common.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单实体类
 *
 * @author xiaoming
 * @date 2023-12-13 20:13
 **/
@Data
@Entity
@Table(name = "sys_menu")
@Schema(description = "菜单实体")
public class MenuEntity extends BaseEntity implements Serializable {

    @Column(columnDefinition = "bigint NOT NULL COMMENT '父菜单ID'")
    @Schema(description = "父菜单ID")
    private Long pid;

    @Column(unique = true, columnDefinition = "varchar(32) NOT NULL COMMENT '菜单名称'")
    @Schema(description = "菜单名称")
    private String name;

    @Column(columnDefinition = "varchar(255) COMMENT '菜单url'")
    @Schema(description = "url")
    private String url;

    @Column(columnDefinition = "varchar(500) COMMENT '授权(多个用逗号分隔，如：user:list,user:create)'")
    @Schema(description = "授权(多个用逗号分隔，如：user:list,user:create)")
    private String perms;

    @Column(columnDefinition = "tinyint(1) NOT NULL COMMENT '菜单类型：0目录，1菜单，2按钮'")
    @Schema(description = "菜单类型：0目录，1菜单，2按钮")
    private Integer type;

    @Column(columnDefinition = "varchar(50) COMMENT '菜单图标'")
    @Schema(description = "菜单图标")
    private String icon;

    @Column(columnDefinition = "tinyint(2) COMMENT '排序'")
    @Schema(description = "排序")
    private Integer sort;

    @JsonIgnore
    @ManyToMany(mappedBy = "menus")
    @Schema(description = "菜单包含的角色")
    private List<RoleEntity> roles;
}
