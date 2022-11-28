package com.tian.springcloud.orderserver.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: tian
 * @Date: 2020/5/31 18:41
 * @Desc:
 */
@RefreshScope
@RestController
@RequestMapping("/show")
public class TestController {
    
    @Value("${order.test}")
    private String orderTest;
    
    @RequestMapping("/test")
    public String test() {
        System.out.println(orderTest);
        return "成功了。。。" + orderTest;
    }
}
