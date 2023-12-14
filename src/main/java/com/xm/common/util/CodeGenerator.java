package com.xm.common.util;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 代码生成器
 *
 * @author xiaoming
 * @date 2023-12-14 21:15
 **/
public class CodeGenerator {

    /**
     * 功能描述: 读取控制台内容
     *
     * @param tip 控制台输入
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入" + tip + "：");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    /**
     * 功能描述: 代码生成
     */
    public static void main(String[] args) {

        // 1、创建代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 2、创建全局配置
        GlobalConfig gc = new GlobalConfig();
        // 获取当前项目的路径
        String projectPath = System.getProperty("user.dir");
        // 文件输出路径
        gc.setOutputDir(projectPath + "/src/main/java");
        // 指定作者
        gc.setAuthor("xiaoming");
        // 生成后是否打开资源管理器
        gc.setOpen(false);
        // 重新生成时文件是否覆盖
        gc.setFileOverride(false);
        // 去掉Service接口的首字母I
        gc.setServiceName("%sService");
        // 修改 Mapper 名称为 Repository
        gc.setMapperName("%sRepository");
        // 添加 Entity 后缀
        gc.setEntityName("%sEntity");
        // 主键生成策略
        gc.setIdType(IdType.AUTO);
        // 定义生成的实体类中日期类型
        gc.setDateType(DateType.ONLY_DATE);
        // 实体属性 Swagger2 注解
        gc.setSwagger2(true);
        // 将全局配置设置到代码生成器对象中
        mpg.setGlobalConfig(gc);


        // 3、创建数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        // 数据库连接
        dsc.setUrl("jdbc:mysql://localhost:3306/xm_admin?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=GMT%2B8");
        // 数据库驱动
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        // 用户名
        dsc.setUsername("root");
        // 密码
        dsc.setPassword("root");
        // 数据库类型
        dsc.setDbType(DbType.MYSQL);
        // 将数据库配置设置到代码生成器对象中
        mpg.setDataSource(dsc);


        // 4、创建包配置
        PackageConfig pc = new PackageConfig();
        // 模块名(在控制台输入)
        pc.setModuleName(scanner("模块名"));
        // 生成文件的上级包
        pc.setParent("com.xm.module");
        // 设置具体包名
        pc.setController("controller");
        pc.setEntity("entity");
        pc.setService("service");
        pc.setMapper("repository");
        // 将包配置设置到代码生成器对象中
        mpg.setPackageInfo(pc);

        // 5、自定义配置
        InjectionConfig customConfig = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("baseEntity", "com.xm.common.base.BaseEntity");
                map.put("jpaRepository", "org.springframework.data.jpa.repository.JpaRepository");
                this.setMap(map);
            }
        };
        mpg.setCfg(customConfig);

        // 6、配置自定义输出模板
        TemplateConfig tc = new TemplateConfig();
        // 指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        tc.setController("freemarker/controller.java");
        tc.setService("freemarker/service.java");
        tc.setServiceImpl("freemarker/serviceImpl.java");
        tc.setMapper("freemarker/repository.java");
        // 设置为null不会生成
        tc.setEntity(null);
        tc.setXml(null);
        mpg.setTemplate(tc);


        // 7、创建策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 数据库表名下划线转驼峰命名
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 数据库列名下划线转驼峰命名
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // 设置lombok模型
        strategy.setEntityLombokModel(true);
        // restful api风格控制器
        strategy.setRestControllerStyle(true);
        // 表名(控制台输入)
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        // url中驼峰转连字符
        strategy.setControllerMappingHyphenStyle(true);
        // 生成实体时去掉表前缀
        strategy.setTablePrefix(pc.getModuleName() + "_");
        // 传入实体类和repository接口的父类
        strategy.setSuperEntityClass("BaseEntity");
        strategy.setSuperMapperClass("JpaRepository");
        // 将生成策略配置设置到代码生成器对象中
        mpg.setStrategy(strategy);

        // 8、修改模板引擎
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        // 9、开始生成代码
        mpg.execute();
    }
}
