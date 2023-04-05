package com.cy.store.mapper;

import com.cy.store.entity.District;

import java.util.List;

public interface DistrictMapper {

    /**
     * 获取全国所有省/某省所有市/某市所有区
     * @param parent 父级代号。获取某市所有区时，使用市代号。获取省所有市时，使用省代号。获取全国所有省时，使用“86作为父级代号”
     * @return 所有省/某省所有市/某市所有区的列表
     */
    List<District> findByParent(String parent);

    /**
     * 根据/省/市/区的行政代号获取省/市/区名称
     * @param Code 省市区的行政代号
     * @return  匹配的省市区名称，没有匹配到数据则返回null
     */
    String findNameByCode(String Code);

}
