package com.felton.mybatismapper.config;

import com.zaxxer.hikari.HikariDataSource;
import io.shardingjdbc.core.api.config.MasterSlaveRuleConfiguration;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

/**
 * ShardingMasterSlaveConfig
 *
 * @author liurui
 * @Description:
 * @date 2020/1/5
 */
@Data
@ConfigurationProperties(prefix = "sharding.jdbc")
public class ShardingMasterSlaveConfig {

    private Map<String, HikariDataSource> dataSources = new HashMap<>();

    private MasterSlaveRuleConfiguration masterSlaveRule;

/*    @Bean
    public ShardingMasterSlaveConfig shardingMasterSlaveConfig() {
        return new ShardingMasterSlaveConfig();
    }*/
}
