package ${package.Service};

import ${package.Entity}.${entity};
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * ${table.comment!} 服务类
 *
 * @author ${author}
 * @date ${date}
 */
public interface ${table.serviceName} {

    /**
     * 分页查询
     *
     * @param pageable 分页参数
     * @return Page<${entity}> 分页数据
     */
    Page<${entity}> page(Pageable pageable);

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return ${entity} 数据
     */
    ${entity} get(Long id);

    /**
     * 根据名称查询
     *
     * @param name 名称
     * @return ${entity} 数据
     */
    ${entity} getByName(String name);

    /**
     * 保存/更新
     *
     * @param params 参数
     */
    void upsert(List<${entity}> params);

    /**
     * 删除
     *
     * @param id id
     */
    void delete(Long id);

}
