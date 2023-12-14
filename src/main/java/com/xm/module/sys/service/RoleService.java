package com.xm.module.sys.service;

import com.xm.module.sys.entity.RoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 *  服务类
 *
 * @author xiaoming
 * @date 2023-12-14
 */
public interface RoleService {

    /**
     * 分页查询
     *
     * @param pageable 分页参数
     * @return Page<RoleEntity> 分页数据
     */
    Page<RoleEntity> page(Pageable pageable);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return RoleEntity 数据
     */
    RoleEntity get(Long id);

    /**
     * 根据名称查询
     *
     * @param name 名称
     * @return RoleEntity 数据
     */
    RoleEntity getByName(String name);

    /**
     * 保存/更新
     *
     * @param params 参数
     */
    void upsert(List<RoleEntity> params);

    /**
     * 删除
     *
     * @param id id
     */
    void delete(Long id);

}
