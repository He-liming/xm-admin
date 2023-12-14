package com.xm.module.sys.entity;

import com.xm.common.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户实体类
 *
 * @author xiaoming
 * @date 2023-12-07 22:57
 **/
@Data
@Entity
@DynamicUpdate
@Table(name = "sys_user")
@Schema(description = "用户实体")
public class UserEntity extends BaseEntity implements Serializable {

    @Column(unique = true, columnDefinition = "varchar(32) NOT NULL COMMENT '名称'")
    @Schema(description = "用户名称")
    private String name;

    @Column(columnDefinition = "char(11) COMMENT '手机号'")
    @Schema(description = "手机号")
    private String mobile;

    @Column(columnDefinition = "varchar(255) COMMENT '头像'")
    @Schema(description = "头像")
    private String avatar;

    @Column(columnDefinition = "varchar(50) COMMENT '邮箱'")
    @Schema(description = "用户邮箱")
    private String email;

    @Column(columnDefinition = "varchar(12) NOT NULL COMMENT '密码'")
    @Schema(description = "用户密码")
    private String password;

    @Column(columnDefinition = "datetime COMMENT '最后登录时间'")
    @Schema(description = "最后登录时间")
    private Date lastLoginTime;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "sys_user_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    @Schema(description = "用户角色")
    private List<RoleEntity> roles;
}
