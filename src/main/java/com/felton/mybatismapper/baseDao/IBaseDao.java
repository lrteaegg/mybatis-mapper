package com.felton.mybatismapper.baseDao;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * IBaseDao
 *
 * @author liurui
 * @Description: 公用Mapper接口
 * @date 2019/12/20
 */
public interface IBaseDao<T> extends Mapper<T>, MySqlMapper<T> {
}
