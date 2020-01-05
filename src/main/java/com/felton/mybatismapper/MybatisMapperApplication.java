package com.felton.mybatismapper;

import com.felton.mybatismapper.config.ShardingMasterSlaveConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@MapperScan("com.felton.mybatismapper.dao")

public class MybatisMapperApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisMapperApplication.class, args);
    }

}
