package com.xm.module.sys.repository;

import com.xm.module.sys.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 *  repository 接口
 *
 * @author xiaoming
 * @date 2023-12-14
 */
@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long>, Serializable {

    /**
     * 根据名称查询
     *
     * @param name 用户名称
     * @return 用户信息
     */
    RoleEntity findByName(String name);
}
