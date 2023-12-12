package com.xm.module.sys.entity;

import com.xm.common.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户实体类
 *
 * @author xiaoming
 * @date 2023-12-07 22:57
 **/
@Data
@Entity
@Table(name = "sys_user")
@Schema(description = "用户实体")
public class UserEntity extends BaseEntity implements Serializable {

    /**
     * Id 表示为表 ID
     * GenerationType.IDENTITY 使用自增长主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "用户ID")
    private Long id;

    /**
     * REQUIRED 表示用户必填
     */
    @Schema(description = "用户名称", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Schema(description = "用户昵称")
    private String nickname;

    @Schema(description = "用户年龄")
    private Integer age;

    /**
     * example 表示示例的值，页面测试可以不用填写直接使用此值。
     */
    @Schema(description = "用户邮箱", example = "xm@xm.com")
    private String email;

    /**
     * hidden 表示不在文档中显示该字段
     */
    @Schema(description = "用户密码", requiredMode = Schema.RequiredMode.REQUIRED, hidden = true)
    private String password;
}
