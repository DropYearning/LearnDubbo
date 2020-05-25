package com.example.gmall.impl;

import org.apache.dubbo.config.annotation.Service;
import org.example.bean.UserAddress;
import org.example.service.UserService;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Service // 注意这个注解是dubbo的，用来暴露服务
public class UserServiceImpl implements UserService {
    public List<UserAddress> getUserAddressList(String userId) {
        UserAddress userAddress1 = new UserAddress(1, "北京中南海", "1", "小明", "13666666666", "Yes");
        UserAddress userAddress2 = new UserAddress(2, "上海外滩", "1", "小明", "13666666666", "Yes");
        return Arrays.asList(userAddress1, userAddress2);
    }
}

