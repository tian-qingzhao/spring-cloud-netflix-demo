package com.tian.springcloud.orderserver.service;

import com.tian.springcloud.orderserver.entity.Order;

/**
 * @Author: tian
 * @Date: 2020/2/24 18:02
 * @Desc:
 */
public interface OrderService {

    Order getById(Long userId, Long productId);
}
