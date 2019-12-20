package com.felton.mybatismapper.config;

import com.felton.mybatismapper.baseDao.IBaseDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import java.util.Properties;

/**
 * MybatisMapperScannerConfig
 *
 * @author liurui
 * @Description: 通用mapper配置
 * @date 2019/12/19
 */
@Configuration
public class MybatisMapperScannerConfig {

    /**
     * 使用通用Mapper之前需要初始化一些信息
     * 切勿使用热加载
     * @return
     */
    @Bean
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
