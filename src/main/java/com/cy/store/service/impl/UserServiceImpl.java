package com.cy.store.service.impl;

import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public void reg(User user) {

        String username = user.getUsername();
        //判断用户名是否被注册过
        User result = userMapper.findByUsername(username);

        if (result != null) {
            throw new UsernameDuplicatedException("用户名被占用");
        }

        //获取盐值
        String oldPassword = user.getPassword();
        String salt = UUID.randomUUID().toString().toUpperCase();
        String md5Password = getMD5Password(oldPassword, salt);
        user.setPassword(md5Password);
        user.setSalt(salt);

        user.setIsDelete(0);
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date  = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);

        Integer rows = userMapper.insert(user);
        if (rows != 1) {
            throw new InsertException("子用户注册过程中产生了位置的异常");

        }


    }


    @Override
    public User login(String username, String password) {

        User result =  userMapper.findByUsername(username);

        if (result == null) {
            throw new UserNotFoundException("用户数据不存在");
        }

        if (result.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据不存在");
        }

        String salt = result.getSalt();
        String oldPassword = result.getPassword();
        String newMd5Password = getMD5Password(password,salt);
        if (!newMd5Password.equals(oldPassword)) {
            throw new PasswordNotMatchException("用户密码错误");
        }

        User user = new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());
        return user;
    }

    // md5算法加密处理
    private String getMD5Password(String password, String salt) {
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();

        }
        return password;

    }

    @Override
    public void changePassword(String oldPassword, Integer uid, String username, String newPassword) {
        User result =  userMapper.findByUid(uid);
        if (result == null || result.getIsDelete() == 1) {
            throw new UserNotFoundException();
        }

        String oldMD5Password =  getMD5Password(oldPassword,result.getSalt());
        if (!result.getPassword().equals(oldMD5Password)) {
            throw new PasswordNotMatchException();
        }

        String newMD5Password = getMD5Password(newPassword, result.getSalt());
        //返回的影响的行数，修改密码应该只返回1
        Integer rows =  userMapper.updatePasswordByUid(uid,newMD5Password,username,new Date());
        if (rows != 1) {
            throw new UpdateException("更新数据产生未知的异常");
        }
    }

    @Override
    public User getByUid(Integer uid) {
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据不存在");
        }

        User user = new User();
        user.setGender(result.getGender());
        user.setEmail(result.getEmail());
        user.setPhone(result.getPhone());
        user.setUsername(result.getUsername());
        return user;
    }

    @Override
    public void changeInfo(Integer uid, String username, User user) {
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据不存在");
        }
        user.setUid(uid);
        user.setModifiedTime(new Date());
        user.setModifiedUser(username);
        Integer rows  = userMapper.updateInfoByUid(user);
        if (rows != 1) {
            throw new UpdateException("更新时数据产生未知异常");
        }
    }

    @Override
    public void changeAvatar(Integer uid, String avatar, String username) {
        //查询用户是否存在
        User result = userMapper.findByUid(uid);
        if (result ==null || result.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据不存在");
        }
        Integer rows = userMapper.updateAvatarByUid(uid,avatar,username,new Date());
        if (rows != 1) {
            throw new UpdateException("更新用户头像产生未知异常");
        }

    }
}
