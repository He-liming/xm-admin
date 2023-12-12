package com.xm.common.base;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

/**
 * 实体类基础属性
 *
 * @author xiaoming
 * @date 2023-12-09 13:52
 **/
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Schema(description = "基础实体")
public class BaseEntity {

    @CreatedBy
    @Column(name = "create_user", updatable = false)
    @Schema(description = "创建用户")
    private String createUser;

    @LastModifiedBy
    @Column(name = "update_user")
    @Schema(description = "修改用户")
    private String updateUser;

    @CreatedDate
    @Column(name = "create_time", updatable = false)
    @Schema(description = "创建时间")
    private Date createTime;

    @LastModifiedDate
    @Column(name = "update_time")
    @Schema(description = "修改时间")
    private Date updateTime;
}
