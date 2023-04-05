package com.cy.store.service.impl;

import com.cy.store.entity.Address;
import com.cy.store.mapper.AddressMapper;
import com.cy.store.service.IAddressService;
import com.cy.store.service.IDistrictService;
import com.cy.store.service.ex.AddressCountLimitException;
import com.cy.store.service.ex.InsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AddressServiceImpl implements IAddressService {
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private IDistrictService iDistrictService;

    @Value("${user.address.max-count}")
    private int maxAddressNumber;

    @Override
    public void addNewAddress(Integer uid, String username, Address address) {

        Integer count = addressMapper.countByUid(uid);
        if (count > maxAddressNumber) {
            throw new AddressCountLimitException("收货地址数量达到上限(" + maxAddressNumber +")");
        }

        //补全数据
        address.setUid(uid);
        Integer isDefault = count == 0 ? 1 : 0;
        address.setIsDefault(isDefault);
        Date now = new Date();
        address.setCreatedUser(username);
        address.setModifiedUser(username);
        address.setModifiedTime(now);
        address.setCreatedTime(now);
        String provinceName = iDistrictService.getNameByCode(address.getProvinceName());
        String cityName = iDistrictService.getNameByCode(address.getCityName());
        String areaName = iDistrictService.getNameByCode(address.getAreaName());
        address.setProvinceName(provinceName);
        address.setCityName(cityName);
        address.setAreaName(areaName);

        Integer rows = addressMapper.insert(address);
        if (rows != 1) {
            throw new InsertException("插入收货地址数据时出现未知错误，请联系系统管理员");
        }

    }
}
