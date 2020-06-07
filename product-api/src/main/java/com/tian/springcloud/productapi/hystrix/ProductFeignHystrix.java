package com.tian.springcloud.productapi.hystrix;

import com.tian.springcloud.productapi.entity.Product;
import com.tian.springcloud.productapi.feign.ProductFeignApi;
import org.springframework.stereotype.Component;

/**
 * @Author: tian
 * @Date: 2020/2/26 17:52
 * @Desc:  指定的Feign客户端接口的回退类。
 *         回退类必须实现有FeignClients注解的接口，并且是有效的spring bean。
 *         返回值为指定的Feign客户端接口的回退类
 */
@Component
public class ProductFeignHystrix implements ProductFeignApi {

    @Override
    public Product getById(Long id) {
        System.out.println("商品的兜底数据。。。。。。");
        return null;
    }
}
