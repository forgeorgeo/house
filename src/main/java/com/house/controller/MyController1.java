package com.house.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/user")
public class MyController1 {

    @RequestMapping(value = "/test1")
    public void test1() throws BusinessException {
        throw new BusinessException("1", "test1 错误");
    }


}