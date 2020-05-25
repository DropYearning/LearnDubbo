package com.example.gmall.impl;

import org.example.bean.UserAddress;
import org.example.service.OrderService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 使用Dubbo调用其他模块的服务过程：
 *  1、将服务提供者注册到注册中心
 *  2、让服务消费者去注册中心订阅服务提供者的服务地址
 */

@Service // 将整个OrderServiceImpl注入到IOC容器中
public class OrderServiceImpl implements OrderService {

    @Autowired
    UserService userService;

    public List<UserAddress> initOrder(String userId) {
        System.out.println("用户id:" + userId);
        // 查询用户的收货地址，需要调用user-service-provider的服务
        List<UserAddress> userAddressList = userService.getUserAddressList(userId);
        for (UserAddress userAddress: userAddressList) {
            System.out.println(userAddress.getUserAddress());
        }
        return userAddressList;
    }
}
