package com.xm.module.sys.entity;

import com.xm.common.base.BaseEntity;
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
public class UserEntity extends BaseEntity implements Serializable {

    /**
     * Id 表示为表 ID
     * GenerationType.IDENTITY 使用自增长主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String nickname;

    private Integer age;

    private String email;

    private String password;
}
