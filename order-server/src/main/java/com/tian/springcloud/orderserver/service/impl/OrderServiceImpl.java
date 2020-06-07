package com.tian.springcloud.orderserver.service.impl;

import com.tian.springcloud.orderserver.entity.Order;
import com.tian.springcloud.orderserver.service.OrderService;
import com.tian.springcloud.productapi.entity.Product;
import com.tian.springcloud.productapi.feign.ProductFeignApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.UUID;

/**
 * @Author: tian
 * @Date: 2020/2/24 18:02
 * @Desc:
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProductFeignApi productFeignApi;

    @Override
    public Order getById(Long userId, Long productId) {
        // 发起远程调用，底层用的是HttpURLConnection，在此处的url里面直接写上服务的名称，
        // 他会从服务列表里面拿到这个服务，这个服务可能会有多个端口号，然后实现负载均衡
        // 使用Ribbon方式远程调用
        // Product product = restTemplate.getForObject("http://PRODUCT-SERVER/server/product/getById?id="+productId,Product.class);

        // 使用feign方式远程调用。 被调用的服务接口没有实现类，此处使用的也是动态代理实现的，
        // 这个productFeignApi类在这里已经变成了一个代理类，代理类底层还是使用的Ribbon方式进行远程调用的
        Product product = productFeignApi.getById(productId);
//        System.out.println(product.toString());
        Order order = new Order();
        //订单编号用uuid生成，然后把每四位中间的那个 - 给替换了
        order.setOrderNo(UUID.randomUUID().toString().replace("-",""));
        order.setCreateTime(new Date());
        if(product != null){
            order.setProductName(product.getName());
            order.setProductPrice(product.getPrice());
        }else {
            order.setProductName("默认名称");
            order.setProductPrice(0);
        }
        order.setUserId(userId);
        //订单保存到数据库
        return order;
    }
}
