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
}
