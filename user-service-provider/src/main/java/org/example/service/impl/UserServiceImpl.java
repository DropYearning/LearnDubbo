package org.example.service.impl;

import org.example.bean.UserAddress;
import org.example.service.UserService;

import java.util.Arrays;
import java.util.List;

public class UserServiceImpl implements UserService {
    public List<UserAddress> getUserAddressList(String userId) {
        UserAddress userAddress1 = new UserAddress(1, "北京中南海", "1", "小明", "13666666666", "Yes");
        UserAddress userAddress2 = new UserAddress(2, "上海外滩", "1", "小明", "13666666666", "Yes");
        return Arrays.asList(userAddress1, userAddress2);
    }
}

