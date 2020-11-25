package com.house.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController2 {

    @RequestMapping(value = "/test2")
    public void test1() throws BusinessException {
        throw new BusinessException("2", "test2 错误");
    }
}