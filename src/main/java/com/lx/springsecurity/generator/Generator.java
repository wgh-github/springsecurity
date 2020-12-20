package com.lx.springsecurity.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * @ClassName: Generator
 * @DestrategyConfigription: 代码自动生成
 * @Author: WGH
 * @Date: 2020/5/18 16:27
 */
public class Generator {

    public static void main(String[] args){


        AutoGenerator generator = new AutoGenerator();

        // 全局变量配置
        GlobalConfig globalConfig = new GlobalConfig();
        String projectPath = System.getProperty("user.dir"); //当前项目
        globalConfig.setOutputDir(projectPath + "/src/main/java"); // 输出路径
        //globalConfig.setOutputDir("G:\\新建文件夹"); // 输出路径
        globalConfig.setFileOverride(true); // 默认 false ,是否覆盖已生成文件
        globalConfig.setOpen(false); //默认true ,是否打开输出目录
        globalConfig.setEnableCache(false); // 默认false,是否开启二级缓存
        globalConfig.setAuthor("wgh"); // 作者
        globalConfig.setSwagger2(true); //默认false
        globalConfig.setBaseResultMap(true); // 默认false
        globalConfig.setDateType(DateType.TIME_PACK); // 时间策略 默认TIME_PACK
        globalConfig.setBaseColumnList(true); //默认false  和basemodel相似
        globalConfig.setEntityName("%s");
        globalConfig.setControllerName("%sController");
        globalConfig.setServiceName("%sService");
        globalConfig.setServiceImplName("%sServiceImpl");
        globalConfig.setMapperName("%sMapper");
        globalConfig.setXmlName("%sMapper");
        globalConfig.setIdType(IdType.ID_WORKER_STR); // 指定生成的主键类型
        generator.setGlobalConfig(globalConfig);



        // 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbQuery(new MySqlQuery()); // 数据库信息查询 //默认mysql
        dataSourceConfig.setDbType(DbType.MYSQL);// 数据库类型
        dataSourceConfig.setTypeConvert(new MySqlTypeConvert()); //类型转换 默认mysql
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/study?serverTimezone=CTT&useUnicode=true&characterEncoding=utf-8&useSSL=false");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("123456");
        dataSourceConfig.setTypeConvert(new MySqlTypeConvert() {// 自定义数据库表字段类型转换【可选】
            @Override
            public DbColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                System.out.println("转换类型：" + fieldType);
                //将数据库中datetime转换成date
                if (fieldType.toLowerCase().contains("datetime")) {
                    return DbColumnType.DATE;
                }
                return (DbColumnType) super.processTypeConvert(globalConfig, fieldType);
            }

        });

        generator.setDataSource(dataSourceConfig);
        // 包配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.lx.springsecurity");
//        pc.setModuleName(""); //此处是所属模块名称
        //packageConfig.setEntity("entity"); //默认entity,controller,service,service.impl,mapper,mapper.xml
        generator.setPackageInfo(packageConfig);


        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        /**
         * 将xml生成到resource下面
         */
/*        String templatePath = "/templates/mapper.xml.ftl"; // framemark
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/"
//                        + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        generator.setCfg(cfg);

        // 配置模板
        TemplateConfig tc = new TemplateConfig();
        tc.setController("templates/controller.java");// /templates/entity.java 模板路径配置，默认再templates
        tc.setXml("");
        generator.setTemplate(tc);*/


        // 数据库表配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setCapitalMode(false); //是否大写命名 默认false
        strategyConfig.setSkipView(true); //是否跳过试图 默认false
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);// 表映射 驼峰命名
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel); // 字段映射 驼峰
        strategyConfig.setEntityLombokModel(true); //默认false
        strategyConfig.setRestControllerStyle(true); // 默认false
        strategyConfig.setEntityColumnConstant(true); //默认false

        strategyConfig.setInclude("user"); //表名，用，隔开  需要生产
        //     strategyConfig.setExclude(""); //                 不需要生成  二选一
        strategyConfig.setEntityTableFieldAnnotationEnable(true); // 默认false 注释
        strategyConfig.setControllerMappingHyphenStyle(false); //默认false
        strategyConfig.setLogicDeleteFieldName("is_delete"); // 逻辑删除字段名称
        generator.setStrategy(strategyConfig);

        // 模板引擎
        generator.setTemplateEngine(new FreemarkerTemplateEngine());

        //执行
        generator.execute();
    }

}

