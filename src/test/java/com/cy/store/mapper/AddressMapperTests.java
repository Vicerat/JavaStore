package com.cy.store.mapper;

import com.cy.store.entity.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressMapperTests {
    @Autowired
    private AddressMapper addressMapper;

    @Test
    public void insert() {
        Address address = new Address();
        address.setUid(1);
        address.setPhone("12345");
        address.setName("孙子");
        addressMapper.insert(address);
    }
    @Test
    public void countByUid() {
        Integer count = addressMapper.countByUid(1);
        System.out.println(count);
    }

    @Test
    public void findByUid() {
        List<Address> addressList = addressMapper.findByUid(1);
        for (Address a : addressList) {
            System.out.println(a);
        }
    }

    @Test
    public void updateNonDefaultByUid() {
        Integer uid = 1;
        Integer rows = addressMapper.updateNonDefaultByUid(uid);
        System.out.println("rows=" + rows);
    }

    @Test
    public void updateDefaultByAid() {
        Integer aid = 1;
        String modifiedUser = "管理员";
        Date modifiedTime = new Date();
        Integer rows = addressMapper.updateDefaultByAid(aid, modifiedUser, modifiedTime);
        System.out.println("rows=" + rows);
    }

    @Test
    public void findByAid() {
        Integer aid = 1;
        Address result = addressMapper.findByAid(aid);
        System.out.println(result);
    }


}
