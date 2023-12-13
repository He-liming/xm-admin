package com.xm.module.sys.service;

import com.xm.module.sys.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author xiaoming
 * @date 2023-12-09 12:41
 **/
public interface UserService {

    /**
     * 分页查询用户
     *
     * @param pageable 分页参数
     * @return Page<UserEntity> 分页用户
     */
    Page<UserEntity> page(Pageable pageable);

    /**
     * 根据ID查询用户
     *
     * @param id 用户ID
     * @return UserEntity 用户信息
     */
    UserEntity get(Long id);

    /**
     * 根据名称查询用户
     *
     * @param name 用户名称
     * @return UserEntity 用户信息
     */
    UserEntity getByName(String name);

    /**
     * 保存/更新用户
     *
     * @param users 用户信息
     */
    void upsert(List<UserEntity> users);

    /**
     * 删除用户
     *
     * @param id 用户id
     */
    void delete(Long id);

}
