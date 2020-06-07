package com.tian.springcloud.productserver.feign;

import com.tian.springcloud.productapi.entity.Product;
import com.tian.springcloud.productapi.feign.ProductFeignApi;
import com.tian.springcloud.productserver.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: tian
 * @Date: 2020/2/25 23:34
 * @Desc:
 */
@RestController
public class ProductFeignClient implements ProductFeignApi {

    @Autowired
    private ProductService productService;

    @Value("${server.port}")
    private String port;

    @Override
    public Product getById(Long id) {
        System.out.println("product-server controller do ...");
        Product product = productService.getById(id);
        Product result = new Product();
        //把product中的属性值拷贝到result对象中，此处这么做的目的是为了不破坏原来的对象的值，真正项目不会这么多，直接追加就可以
        BeanUtils.copyProperties(product,result);
        result.setName(result.getName() + ",data from port "+port);
      /*  try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        return result;
    }
}
