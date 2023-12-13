package com.xm.module.sys.repository;

import com.xm.module.sys.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * @author xiaoming
 * @date 2023-12-08 23:25
 **/
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>, Serializable {

    /**
     * 根据用户名称查询用户
     * 这个相当与一个公式， findBy字段名称，jpa 就会自动实现查询，不用我们再写查询逻辑
     *
     * @param name 用户名称
     * @return 用户信息
     */
    UserEntity findByName(String name);

}
