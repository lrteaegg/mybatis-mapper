package com.felton.mybatismapper.service;

import com.felton.mybatismapper.domain.entity.Country;

import java.util.List;

/**
 * ICountryService
 *
 * @author liurui
 * @Description: 业务层
 * @date 2019/12/20
 */
public interface ICountryService {
    int delectByPrimaryKey(Integer id);

    List<Country> listCountry();
}
