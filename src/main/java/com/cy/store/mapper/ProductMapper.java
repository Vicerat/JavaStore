package com.cy.store.mapper;

import com.cy.store.entity.Product;

import java.util.List;

public interface ProductMapper {

    /**
     * 查询热销商品的前四名
     * @return 热销商品的前四名集合
     */
    List<Product> findHotList();
}
