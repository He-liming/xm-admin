package com.xm.common.base;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "用户ID")
    private Long id;

    @Column(columnDefinition = "tinyint(1) NOT NULL COMMENT '状态'")
    @Schema(description = "状态")
    private Integer status;

    @Column(columnDefinition = "bit NOT NULL COMMENT '0正常，1删除'")
    @Schema(description = "逻辑删除")
    private Boolean isDelete;

    @CreatedBy
    @Column(updatable = false, columnDefinition = "varchar(32) COMMENT '创建用户'")
    @Schema(description = "创建用户", hidden = true)
    private String createUser;

    @LastModifiedBy
    @Column(columnDefinition = "varchar(32) COMMENT '修改用户'")
    @Schema(description = "修改用户", hidden = true)
    private String updateUser;

    @CreatedDate
    @Column(updatable = false, columnDefinition = "datetime COMMENT '创建时间'")
    @Schema(description = "创建时间", hidden = true)
    private Date createTime;

    @LastModifiedDate
    @Column(columnDefinition = "datetime COMMENT '修改时间'")
    @Schema(description = "修改时间", hidden = true)
    private Date updateTime;
}
