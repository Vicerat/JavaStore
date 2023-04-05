package com.cy.store.mapper;

import com.cy.store.entity.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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


}
