package com.cy.store.service;

import com.cy.store.entity.User;

import java.util.Date;

public interface IUserService {
    /**
    * 用户注册方法
    * @param user 用户的数据对象
    * */
    void reg(User user);

    /**
     * 用户登录功能
     * @param username 用户名
     * @param password 用户的密码
     * @return 返回当前匹配的用户数据，如果没有则返回null
     */
    User login(String username, String password);

    void changePassword(String oldPassword,Integer uid,String username,String newPassword);

    User getByUid(Integer id);

    void changeInfo(Integer uid,String username, User user);

    void changeAvatar(Integer uid,String avatar, String username);
}
