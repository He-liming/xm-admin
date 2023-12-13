package com.xm.module.sys.service.impl;

import com.xm.common.util.JpaUtils;
import com.xm.module.sys.entity.UserEntity;
import com.xm.module.sys.repository.UserRepository;
import com.xm.module.sys.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author xiaoming
 * @date 2023-12-09 12:42
 **/
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Override
    public Page<UserEntity> page(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public UserEntity get(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public UserEntity getByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public void upsert(List<UserEntity> users) {
        users.forEach(user -> {
            Optional<UserEntity> userEntity = userRepository.findById(user.getId());
            if (userEntity.isPresent()) {
                // 修改
                JpaUtils.copyNotNullProperties(user, userEntity.get());
                userRepository.save(userEntity.get());
            } else {
                // 新增
                userRepository.save(user);
            }
        });
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

}
