package com.cy.store.service;

import com.cy.store.entity.Address;

import java.util.List;

public interface IAddressService {
    /**
     * 添加新的收货地址
     * @param uid 当前登录用户的id
     * @param username 当前登录用户的用户名
     * @param address 用户提交的收货地址数据
     */
    void addNewAddress(Integer uid, String username, Address address);

    List<Address> getByUid(Integer uid);

    /**
     * 设置默认收货地址
     * @param aid 收货地址id
     * @param uid 归属用户id
     * @param username 当前登录用户名
     */
    void setDefault(Integer aid, Integer uid, String username);

    /**
     * 删除收货地址
     * @param aid 收货地址id
     * @param uid 归属的用户id
     * @param username 当前登录的用户名
     */
    void delete(Integer aid, Integer uid, String username);
}
