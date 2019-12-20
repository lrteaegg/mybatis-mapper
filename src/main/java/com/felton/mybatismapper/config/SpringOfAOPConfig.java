package com.felton.mybatismapper.config;

import com.felton.mybatismapper.aspect.LogAopAspect;
import com.felton.mybatismapper.aspect.MathAspects;
import com.felton.mybatismapper.util.MathCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author LiuRui
 * @version 1.0
 * @date 2019/12/20 13:47
 * @description
 */
@Configuration
public class SpringOfAOPConfig {

    @Bean
    public MathCalculator mathCalculator() {
        return new MathCalculator();
    }

    /*@Bean
    public MathAspects mathAspects() {
        return new MathAspects();
    }*/

/*    @Bean
    public LogAopAspect logAopAspect() {
        return new LogAopAspect();
    }*/

}
