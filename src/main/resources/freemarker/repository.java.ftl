package ${package.Mapper};

import ${package.Entity}.${entity};
import ${cfg.jpaRepository};
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * ${table.comment!} repository 接口
 *
 * @author ${author}
 * @date ${date}
 */
@Repository
public interface ${table.mapperName} extends ${superMapperClass}<${entity}, Long>, Serializable {

    /**
     * 根据名称查询
     *
     * @param name 用户名称
     * @return 用户信息
     */
    ${entity} findByName(String name);
}
