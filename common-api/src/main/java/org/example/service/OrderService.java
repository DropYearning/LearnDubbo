package org.example.service;

import org.example.bean.UserAddress;

import java.util.List;

public interface OrderService {

    // 初始化订单
    public List<UserAddress> initOrder(String user);
}
