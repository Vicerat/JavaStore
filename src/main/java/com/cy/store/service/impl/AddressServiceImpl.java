package com.cy.store.service.impl;

import com.cy.store.entity.Address;
import com.cy.store.mapper.AddressMapper;
import com.cy.store.service.IAddressService;
import com.cy.store.service.IDistrictService;
import com.cy.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

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
        String provinceName = iDistrictService.getNameByCode(address.getProvinceCode());
        String cityName = iDistrictService.getNameByCode(address.getCityCode());
        String areaName = iDistrictService.getNameByCode(address.getAreaCode());
        address.setProvinceName(provinceName);
        address.setCityName(cityName);
        address.setAreaName(areaName);

        Integer rows = addressMapper.insert(address);
        if (rows != 1) {
            throw new InsertException("插入收货地址数据时出现未知错误，请联系系统管理员");
        }

    }

    @Override
    public List<Address> getByUid(Integer uid) {

        List<Address> addressList = addressMapper.findByUid(uid);
        for (Address address : addressList) {
            address.setUid(null);
            address.setProvinceCode(null);
            address.setCityCode(null);
            address.setAreaCode(null);
            address.setCreatedUser(null);
            address.setCreatedTime(null);
            address.setModifiedUser(null);
            address.setModifiedTime(null);
        }
        return addressList;
    }

    @Override
    public void setDefault(Integer aid, Integer uid, String username) {
        Address address = addressMapper.findByAid(aid);
        if (address == null) {
            throw new AddressNotFoundException("尝试访问的地址不存在");
        }

        if (address.getUid() != uid) {
            throw new AccessDeniedException("非法访问异常");
        }

        Integer rows = addressMapper.updateNonDefaultByUid(uid);
        if (rows < 0) {
            throw new UpdateException("设置默认收货地址时出现未知错误[1]");
        }

        rows = addressMapper.updateDefaultByAid(aid,username,new Date());
        if (rows != 1) {
            throw new UpdateException("设置默认收货地址时出现未知异常[2]");
        }
    }

    @Transactional
    @Override
    public void delete(Integer aid, Integer uid, String username) {
        Address result = addressMapper.findByAid(aid);
        if (result == null) {
            throw new AddressNotFoundException("尝试访问的收货地址数据不存在");
        }

        if (uid != result.getUid()) {
            throw new AccessDeniedException("非法访问");
        }

        Integer rows = addressMapper.deleteByAid(aid);
        if (rows != 1) {
            throw new DeleteException("删除收货地址数据时出现未知错误");
        }

        if (result.getIsDefault() == 0) {
            return;
        }

        Integer count = addressMapper.countByUid(uid);
        if (count == 0) {
            return;
        }

        Address lastModified = addressMapper.findLastModified(uid);
        Integer lastModifiedAid = lastModified.getAid();

        rows = addressMapper.updateDefaultByAid(lastModifiedAid,username,new Date());
        if (rows != 1) {
            throw new UpdateException("更新收货地址时出现未知错误");
        }

    }

    /*
    *
    *
    *
    * */
}
