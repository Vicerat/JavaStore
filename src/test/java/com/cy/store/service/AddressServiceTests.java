package com.cy.store.service;

import com.cy.store.entity.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressServiceTests {
    @Autowired
    private IAddressService iAddressService;

    @Test
    public void addNewAddress() {
        Address address = new Address();
        Integer uid = 1;
        String username = "zcc";
        address.setName("孙子");
        address.setPhone("123456789");
        address.setAddress("天堂");
        iAddressService.addNewAddress(uid,username,address);
    }

    @Test
    public void getBYUid() {

        List<Address> addressList = iAddressService.getByUid(1);
        for (Address address : addressList) {
            System.out.println(address);
        }
    }
}
