package ${package.ServiceImpl};

import com.xm.common.util.JpaUtils;
import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * ${table.comment!} 服务实现类
 *
 * @author ${author}
 * @date ${date}
 */
@Service
public class ${table.serviceImplName} implements ${table.serviceName} {

    @Resource
    private ${table.mapperName} repository;

    @Override
    public Page<${entity}> page(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public ${entity} get(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public ${entity} getByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public void upsert(List<${entity}> data) {
        data.forEach(d -> {
            Optional<${entity}> entity = repository.findById(d.getId());
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
