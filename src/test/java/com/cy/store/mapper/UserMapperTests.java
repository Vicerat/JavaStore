package com.cy.store.mapper;

import com.cy.store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void insert() {
        User user = new User();
        user.setUsername("tim");
        user.setPassword("123");
        Integer rows = userMapper.insert(user);
        System.out.println("rows");

    }

    @Test
    public void findByUsername() {
        User user = userMapper.findByUsername("tim");
        System.out.println(user);
    }

    @Test
    public void updatePasswordByUid() {
        userMapper.updatePasswordByUid(8,"321","zcc",new Date());
    }

    @Test
    public void findByUid() {
        System.out.println(userMapper.findByUid(1));
    }

    @Test
    public void updateInfoByUid() {
        User user = new User();
        user.setUid(10);
        user.setPhone("123456789");
        user.setEmail("123@qq.com");
        user.setGender(1);
        userMapper.updateInfoByUid(user);
    }

    @Test
    public void updateAvatarByUid() {
        userMapper.updateAvatarByUid(1,"/upload/avatar.png","管理员",new Date());
    }


}
