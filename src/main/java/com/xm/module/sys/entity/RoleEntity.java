package com.xm.module.sys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xm.common.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 角色实体类
 *
 * @author xiaoming
 * @date 2023-12-13 20:09
 **/
@Data
@Entity
@Table(name = "sys_role")
@Schema(description = "角色实体")
public class RoleEntity extends BaseEntity implements Serializable {

    @Column(unique = true, columnDefinition = "varchar(32) NOT NULL COMMENT '角色名称'")
    @Schema(description = "角色名称")
    private String name;

    @Column(columnDefinition = "varchar(500) COMMENT '备注'")
    @Schema(description = "备注")
    private String remark;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    @Schema(description = "角色包含的用户")
    private List<UserEntity> users;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "sys_role_menu",
            joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "menu_id", referencedColumnName = "id")})
    @Schema(description = "角色菜单")
    private List<MenuEntity> menus;
}
