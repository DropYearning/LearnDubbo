package org.example.service;

import org.example.bean.UserAddress;

import java.util.List;

/**
 * 用户服务接口
 */
public interface UserService {

    // 根据用户id获取用户所有的收货地址
    public List<UserAddress> getUserAddressList(String userId);
}
