package com.cy.store.service;

import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTests {
    @Autowired
    private IUserService iUserService;

    @Test
    public void reg() {
        try {
            User user = new User();
            user.setUsername("regUser2");
            user.setPassword("123");
            iUserService.reg(user);
            System.out.println(user.getUsername());
            System.out.println(user.getPassword());
        } catch (ServiceException e) {
            //获取类的对象，再获取类的名称
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }

    }
    @Test
    public void login(){
        User user = iUserService.login("zcc","123");
        System.out.println(user);
    }

    @Test
    public void changePassword() {
        iUserService.changePassword("123",10,"changePassword","321");
    }

    @Test
    public void getByUid() {
        User result = iUserService.getByUid(1);
        System.out.println(result);

    }

    @Test
    public void changeInfo() {
        User user = new User();
        user.setPhone("123");
        user.setEmail("123@qq.com");
        user.setGender(1);
        iUserService.changeInfo(8,"zcc",user);

        User result = iUserService.getByUid(8);
        System.out.println(result);
    }

    @Test
    public void changeAvatar() {
        iUserService.changeAvatar(1,"/upload/test.png","小明");
    }

}
