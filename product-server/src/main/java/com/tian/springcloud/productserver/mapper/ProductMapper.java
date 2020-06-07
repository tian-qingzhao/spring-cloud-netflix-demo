package com.tian.springcloud.productserver.mapper;

import com.tian.springcloud.productapi.entity.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: tian
 * @Date: 2020/2/24 14:06
 * @Desc: 模拟从数据库拿到数据
 */
@Component
public class ProductMapper {

    private Map<Long, Product> productMap = new HashMap<Long, Product>();

    public ProductMapper(){
        Product p1 = new Product(1l,"苹果手机",8999,20);
        Product p2 = new Product(2l,"华为手机",5589,30);
        Product p3 = new Product(3l,"小米手机",3989,15);
        Product p4 = new Product(4l,"OPPO手机",2982,10);
        Product p5 = new Product(5l,"VIVO手机",1799,5);
        productMap.put(p1.getId(),p1);
        productMap.put(p2.getId(),p2);
        productMap.put(p3.getId(),p3);
        productMap.put(p4.getId(),p4);
        productMap.put(p5.getId(),p5);
    }

    public List<Product> list(){
        return new ArrayList<>(productMap.values());
    }

    public Product getById(Long id){
        return productMap.get(id);
    }
}
