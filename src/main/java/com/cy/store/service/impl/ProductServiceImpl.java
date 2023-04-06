package com.cy.store.service.impl;

import com.cy.store.entity.Product;
import com.cy.store.mapper.ProductMapper;
import com.cy.store.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;


    @Override
    public List<Product> findHotList() {
        List<Product> list = productMapper.findHotList();
        for (Product p : list) {
            p.setPriority(null);
            p.setModifiedUser(null);
            p.setCreatedTime(null);
            p.setCreatedUser(null);
            p.setModifiedTime(null);
        }
        return list;
    }
}
