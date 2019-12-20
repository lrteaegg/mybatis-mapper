package com.felton.mybatismapper.service.impl;

import com.felton.mybatismapper.service.ICountryService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CountryServiceImplTest {
    @Autowired
    private ICountryService countryService;

    @Test
    void delectByPrimaryKey() {
        Integer result = countryService.delectByPrimaryKey(1);
        System.out.println(result);
    }
}