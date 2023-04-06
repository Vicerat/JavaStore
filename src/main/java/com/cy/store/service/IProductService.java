package com.cy.store.service;

import com.cy.store.entity.Product;

import java.util.List;

public interface IProductService {
    List<Product> findHotList();

    Product findById(Integer id);
}
