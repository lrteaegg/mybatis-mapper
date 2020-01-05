package com.felton.mybatismapper.service;

import com.felton.mybatismapper.domain.entity.Country;

/**
 * ICountryService
 *
 * @author liurui
 * @Description: 业务层
 * @date 2019/12/20
 */
public interface ICountryService {
    int delectByPrimaryKey(Integer id);
    int addCountry(Country country);

    /**
     * 批量添加本地文件到数据库
     * @return
     */
    int addLocalCountry();
}
