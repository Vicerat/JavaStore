package com.cy.store.mapper;

import com.cy.store.entity.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DistrictMapperTests {
    @Autowired
    private DistrictMapper districtMapper;

    @Test
    public void findByParent() {
        String parent = "110100";
        List<District> list = districtMapper.findByParent(parent);
        for (District district : list) {
            System.out.println(district);
        }
    }

    @Test
    public void findNameByCode() {
        String name = districtMapper.findNameByCode("540000");
        System.out.println(name);
    }


}
