package com.cy.store.mapper;

import com.cy.store.entity.Address;

import java.util.List;

public interface AddressMapper {

    /**
     * 插入用户的收货地址数据
     * @param address 收货地址数据
     * @return 受影响的行数
     */
    Integer insert(Address address);

    /**
     * 根据用户的id同级收货地址数量
     * @param uid 用户的id
     * @return 当前用户收货地址总数
     */
    Integer countByUid(Integer uid);

    List<Address> findByUid(Integer uid);

}
