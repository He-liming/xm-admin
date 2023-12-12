package com.xm.module.sys.service.impl;

import com.xm.module.sys.entity.UserEntity;
import com.xm.module.sys.repository.UserRepository;
import com.xm.module.sys.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiaoming
 * @date 2023-12-09 12:42
 **/
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Override
    public UserEntity get(Long id) {
        return userRepository.getReferenceById(id);
    }

    @Override
    public List<UserEntity> lists() {
        return userRepository.findAll();
    }

    @Override
    public void save(UserEntity user) {
        userRepository.save(user);
    }

    @Override
    public void update(UserEntity user) {
        userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Page<UserEntity> page(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
