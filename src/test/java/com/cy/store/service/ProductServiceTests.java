package com.cy.store.service;

import com.cy.store.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductServiceTests {
    @Autowired
    private IProductService iProductService;

    @Test
    public void findHotList() {
        List<Product> hotList = iProductService.findHotList();
        System.out.println("count=" + hotList.size());
        for (Product item : hotList) {
            System.out.println(item);
        }

    }
}
