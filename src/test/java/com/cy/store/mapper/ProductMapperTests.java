package com.cy.store.mapper;

import com.cy.store.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductMapperTests {
    @Autowired
    private ProductMapper productMapper;

    @Test
    public void findHotList() {
        List<Product> list = productMapper.findHotList();
        for (Product product : list) {
            System.out.println(product);
        }
    }

    @Test
    public void findById() {
        Integer id = 10000017;
        Product result = productMapper.findById(id);
        System.out.println(result);
    }


}
