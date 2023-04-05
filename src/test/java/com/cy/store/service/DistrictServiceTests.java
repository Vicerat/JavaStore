package com.cy.store.service;

import com.cy.store.entity.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DistrictServiceTests {
    @Autowired
    private IDistrictService iDistrictService;

    @Test
    public void getByParent() {

        List<District> districtList = iDistrictService.getByParent("110100");
        for (District district : districtList) {
            System.out.println(district);
        }
    }

    @Test
    public void getNameByCode() {
        System.out.println(iDistrictService.getNameByCode("430000"));
    }
}
