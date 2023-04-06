package com.cy.store.controller;

import com.cy.store.entity.Address;
import com.cy.store.service.IAddressService;
import com.cy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("addresses")
public class AddressController extends BaseController{
    @Autowired
    private IAddressService addressService;

    @RequestMapping("add_new_address")
    public JsonResult<Void> addNewAddress(Address address, HttpSession session) {
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);

        addressService.addNewAddress(uid,username,address);
        return new JsonResult<Void>(Ok);
    }

    @RequestMapping({"/",""})
    public JsonResult<List<Address>> getByUid(HttpSession session) {
        Integer uid = getuidFromSession(session);
        List<Address> addressList = addressService.getByUid(uid);
        return new JsonResult<List<Address>>(Ok,addressList);
    }

    @RequestMapping("{aid}/set_default")
    public JsonResult<Void> setDefaultAddresss(@PathVariable("aid") Integer aid, HttpSession session) {
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        addressService.setDefault(aid,uid,username);
        return new JsonResult<Void>(Ok);
    }

    @RequestMapping("{aid}/delete")
    public JsonResult<Void> deleteAddress(@PathVariable("aid") Integer aid,HttpSession session) {
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        addressService.delete(aid,uid,username);
        return new JsonResult<Void>(Ok);
    }
}
