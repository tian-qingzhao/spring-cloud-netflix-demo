package com.tian.springcloud.orderserver.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.tian.springcloud.orderserver.entity.Order;
import com.tian.springcloud.orderserver.service.OrderService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * @Author: tian
 * @Date: 2020/2/24 18:00
 * @Desc:
 */
@RestController
@RequestMapping("/order/server")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping("/getById")
    @HystrixCommand(fallbackMethod = "error") // 参数为降级的方法名
    public Order getById(Long userId, Long productId, HttpServletRequest request){
        System.out.println("doOrderController ......");
//        int i =1/0;
        return orderService.getById(userId,productId);
    }

    public Order error(Long userId,Long productId, HttpServletRequest request){
        System.out.println("doError......");
        new Thread(()->{
            String key = "order-server";
            String value = redisTemplate.opsForValue().get(key);
            if(StringUtils.isEmpty(value)){
                // 用户下订单失败，发送告诉运维人员赶快检查问题
                System.out.println("用户下单失败，请运维人员赶快修复");
                redisTemplate.opsForValue().set(key,"order-server-fail",20, TimeUnit.SECONDS);
            }else {
                System.out.println("已经发送过短信了。。。。。");
            }
        }).start();
        return new Order();
    }

    @RequestMapping("/test")
    public String test(){
        return "teshi";
    }

    @RequestMapping("/main")
    public String main(){
        return "main";
    }
}
