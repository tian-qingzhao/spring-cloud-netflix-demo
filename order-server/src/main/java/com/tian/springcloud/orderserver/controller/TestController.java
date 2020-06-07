package com.tian.springcloud.orderserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: tian
 * @Date: 2020/5/31 18:41
 * @Desc:
 */
@Controller
@RequestMapping("/show")
public class TestController {

    @RequestMapping("/test")
    public String test(){
        return "成功了。。。";
    }
}
