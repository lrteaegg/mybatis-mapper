package com.felton.mybatismapper.config;

import com.felton.mybatismapper.baseDao.IBaseDao;
import com.google.common.collect.Maps;
import io.shardingjdbc.core.api.MasterSlaveDataSourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

/**
 * MybatisMapperScannerConfig
 *
 * @author liurui
 * @Description: 通用mapper配置
 * @date 2019/12/19
 */
@Configuration
@EnableConfigurationProperties(ShardingMasterSlaveConfig.class)
@ConditionalOnProperty({ "sharding.jdbc.data-sources.ds_master.jdbc-url",
        "sharding.jdbc.master-slave-rule.master-data-source-name" })
public class MybatisMapperScannerConfig {
//
    /*@Autowired
    private ShardingMasterSlaveConfig shardingMasterSlaveConfig*/;

    @Bean
    public DataSource masterSlaveDataSource(ShardingMasterSlaveConfig shardingMasterSlaveConfig) throws SQLException {
        final Map<String, DataSource> dataSourceMap = Maps.newHashMap();
        dataSourceMap.putAll(shardingMasterSlaveConfig.getDataSources());
        final Map<String, Object> newHashMap = Maps.newHashMap();
        // 创建 MasterSlave数据源
        DataSource dataSource = MasterSlaveDataSourceFactory.createDataSource(dataSourceMap,
                shardingMasterSlaveConfig.getMasterSlaveRule(), newHashMap);
//        log.info("masterSlaveDataSource config complete");
        return dataSource;
    }

    /**
     * 使用通用Mapper之前需要初始化一些信息
     * 切勿使用热加载
     * @return
     */
    @Bean
//    @DependsOn("shardingMasterSlaveConfig")
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("com.felton.mybatismapper.dao");
        Properties properties = new Properties();
        properties.setProperty("mappers", IBaseDao.class.getName());
        properties.setProperty("notEmpty", "false");
        properties.setProperty("IDENTITY", "MYSQL");

        mapperScannerConfigurer.setProperties(properties);
        return mapperScannerConfigurer;
    }




}
