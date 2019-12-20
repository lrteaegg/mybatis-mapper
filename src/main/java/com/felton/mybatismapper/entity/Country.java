package com.felton.mybatismapper.entity;


import lombok.Data;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Repository
@Table(name = "country")
@Data
public class Country implements Serializable {
    @Id
    private Integer id;
    private String  countryName;
    private String  countryCode;

    //省略 getter 和 setter
}