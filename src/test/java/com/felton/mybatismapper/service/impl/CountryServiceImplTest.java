package com.felton.mybatismapper.service.impl;

import com.felton.mybatismapper.dao.ICountryDao;
import com.felton.mybatismapper.domain.entity.Country;
import com.felton.mybatismapper.service.ICountryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class CountryServiceImplTest {

    @Autowired
    private ICountryService countryService;
    @Autowired
    private ICountryDao countryDao;

    @Test
    void delectByPrimaryKey() {
        Integer result = countryService.delectByPrimaryKey(1);
        System.out.println(result);
    }

    @Test
    public void test1() {
        log.debug("debug test");
        log.info("info test");
        log.warn("warn test");
    }

    @Test
    void addLocalCountry() {
        countryService.addLocalCountry();
    }

    @Test
    void pageHelperTest() {
        PageHelper.startPage(0 , 5);
        List<Country> result = countryDao.selectAll();
        System.out.println(result.size());
        for (Country country : result) {
            System.out.println(country.getCountryName());
        }
    }
}