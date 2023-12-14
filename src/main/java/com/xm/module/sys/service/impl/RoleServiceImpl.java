package com.xm.module.sys.service.impl;

import com.xm.common.util.JpaUtils;
import com.xm.module.sys.entity.RoleEntity;
import com.xm.module.sys.repository.RoleRepository;
import com.xm.module.sys.service.RoleService;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *  服务实现类
 *
 * @author xiaoming
 * @date 2023-12-14
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleRepository repository;

    @Override
    public Page<RoleEntity> page(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public RoleEntity get(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public RoleEntity getByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public void upsert(List<RoleEntity> data) {
        data.forEach(d -> {
            Optional<RoleEntity> entity = repository.findById(d.getId());
            if (entity.isPresent()) {
                // 修改
                JpaUtils.copyNotNullProperties(d, entity.get());
                repository.save(entity.get());
            } else {
                // 新增
                repository.save(d);
            }
        });
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
