package com.cy.store.service;

import com.cy.store.entity.Address;

public interface IAddressService {
    /**
     * 添加新的收货地址
     * @param uid 当前登录用户的id
     * @param username 当前登录用户的用户名
     * @param address 用户提交的收货地址数据
     */
    void addNewAddress(Integer uid, String username, Address address);
}
