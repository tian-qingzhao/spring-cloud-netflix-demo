package com.tian.springcloud.productserver.service.impl;

import com.tian.springcloud.productapi.entity.Product;
import com.tian.springcloud.productserver.mapper.ProductMapper;
import com.tian.springcloud.productserver.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: tian
 * @Date: 2020/2/24 14:20
 * @Desc:
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> list() {
        return productMapper.list();
    }

    @Override
    public Product getById(Long id) {
        return productMapper.getById(id);
    }
}
