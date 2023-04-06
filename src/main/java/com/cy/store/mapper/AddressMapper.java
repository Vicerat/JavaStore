package com.cy.store.mapper;

import com.cy.store.entity.Address;

import java.util.Date;
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

    /**
     * 根据用户id查找其所有地址
     * @param uid 用户id
     * @return 返回用户的地址列表
     */
    List<Address> findByUid(Integer uid);

    /**
     * 将某用户的所有收货地址设置为非默认地址
     * @param uid 收货地址归属的用户id
     * @return 受影响的数据行数
     */
    Integer updateNonDefaultByUid(Integer uid);


    /**
     * 将制定的收货地址设置为默认地址
     * @param aid 收货地址id
     * @param modifiedUser 修改执行人
     * @param modifiedTime 修改时间
     * @return 受影响的行数
     */
    Integer updateDefaultByAid(Integer aid, String modifiedUser, Date modifiedTime);

    /**
     * 根据收货地址的aid值，查询收货地址详情
     * @param aid 收货地址id
     * @return 匹配的收货地址详情，如果没有匹配的数据则返回null
     */
    Address findByAid(Integer aid);



}
