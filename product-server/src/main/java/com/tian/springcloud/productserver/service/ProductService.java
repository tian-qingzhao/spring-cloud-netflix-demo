package com.tian.springcloud.productserver.service;

import com.tian.springcloud.productapi.entity.Product;

import java.util.List;

/**
 * @Author: tian
 * @Date: 2020/2/24 14:19
 * @Desc:
 */
public interface ProductService {

    public List<Product> list();

    public Product getById(Long id);
}
