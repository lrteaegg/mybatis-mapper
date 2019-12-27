package com.felton.mybatismapper.entity;

import lombok.Data;
import org.springframework.stereotype.Repository;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author LiuRui
 * @version 1.0
 * @date 2019/12/25 13:47
 * @description
 */
@Table(name = "picture")
@Data
@Repository
public class Picture {

    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    private String name; // 图片名称

    private byte[] content; // 图片内容
}
