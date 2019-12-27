package com.felton.mybatismapper.dao;

import com.felton.mybatismapper.baseDao.IBaseDao;
import com.felton.mybatismapper.entity.Picture;

/**
 * @author LiuRui
 * @version 1.0
 * @date 2019/12/25 13:52
 * @description
 */
public interface IPictureDao extends IBaseDao<Picture> {
    int insertSetBean(Picture picture);
}
