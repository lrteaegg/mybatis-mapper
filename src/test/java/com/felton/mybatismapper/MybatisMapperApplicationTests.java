package com.felton.mybatismapper;

import com.felton.mybatismapper.config.SpringOfAOPConfig;
import com.felton.mybatismapper.util.MathCalculator;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class MybatisMapperApplicationTests {

    @Test
    void contextLoads() {
        AnnotationConfigApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(SpringOfAOPConfig.class);
        MathCalculator mathCalculator = applicationContext.getBean(MathCalculator.class);

        mathCalculator.div(1, 1);

        applicationContext.close();
    }

}
