package com.felton.mybatismapper.controller;

import com.felton.mybatismapper.annotation.LogAnno;
import com.felton.mybatismapper.service.ICountryService;
import com.felton.mybatismapper.util.MathCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author LiuRui
 * @version 1.0
 * @date 2019/12/20 10:01
 * @description
 */
@Controller
@RequestMapping("country")
public class CountryController {
    @Autowired
    private ICountryService countryService;

    @RequestMapping("delete")
    @ResponseBody
    public int deleteCountry(Integer id) {
        return countryService.delectByPrimaryKey(id);
    }

    @LogAnno(operateType = "test")
    @RequestMapping("computer")
    @ResponseBody
    public int computer(Integer integer) {
        MathCalculator mathCalculator = new MathCalculator();
        return mathCalculator.div(1, 1);
    }

    /**
     * 批量添加图片
     * @return
     */
    @RequestMapping(value = "/batch-add", method = RequestMethod.GET)
    public int batchAddCountry() {
        return countryService.addLocalCountry();
    }
}
