package com.cy.store.controller;

import com.cy.store.entity.District;
import com.cy.store.service.IDistrictService;
import com.cy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("districts")
public class DistrictController extends BaseController{
    @Autowired
    private IDistrictService iDistrictService;

    @RequestMapping({"/",""})
    public JsonResult<List<District>> getByParent(String parent) {

        List<District> data = iDistrictService.getByParent(parent);
        return new JsonResult<List<District>>(Ok,data);
    }

}
