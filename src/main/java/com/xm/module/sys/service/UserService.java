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
     * 根据ID查询用户
     *
     * @param id 用户ID
     * @return UserEntity 用户信息
     */
    UserEntity get(Long id);

    /**
     * 查询全部用户
     *
     * @return List<UserEntity> 用户集合
     */
    List<UserEntity> lists();

    /**
     * 保存用户
     *
     * @param user 用户信息
     */
    void save(UserEntity user);

    /**
     * 修改用户
     *
     * @param user 用户信息
     */
    void update(UserEntity user);

    /**
     * 删除用户
     *
     * @param id 用户id
     */
    void delete(Long id);

    /**
     * 分页查询用户
     *
     * @param pageable 分页参数
     * @return Page<UserEntity> 分页用户
     */
    Page<UserEntity> page(Pageable pageable);

}
