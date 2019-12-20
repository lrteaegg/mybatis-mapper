package com.felton.mybatismapper.service.impl;

import com.felton.mybatismapper.dao.ICountryDao;
import com.felton.mybatismapper.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
