package com.felton.mybatismapper.service.impl;

import com.felton.mybatismapper.dao.ICountryDao;
import com.felton.mybatismapper.domain.entity.Country;
import com.felton.mybatismapper.service.ICountryService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CountryServiceImpl
 *
 * @author liurui
 * @Description:
 * @date 2019/12/20
 */
@Service
public class CountryServiceImpl implements ICountryService {

    @Autowired
    private ICountryDao countryDao;


    @Override
    public int delectByPrimaryKey(Integer id) {
        int row = countryDao.deleteByPrimaryKey(id);
        return row;
    }

    @Override
    public List<Country> listCountry() {
        PageHelper.startPage(1,5);
        return countryDao.selectAll();
    }


}
