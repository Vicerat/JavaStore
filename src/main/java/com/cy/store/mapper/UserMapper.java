package com.cy.store.mapper;

import com.cy.store.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

//用户模块的持久层接口
//@Mapper
public interface UserMapper {

    /*
    * 插入用户的数据
    * @param user用户的数据
    * @ruturn 受影响的行数（增删改，都受影响的行数作为返回值，可以根据返回值来判断是否执行成功）
    *
    * */
    Integer insert(User user);
    /*
    * 根据用户名查询用户数据
    * @param username 用户名
    * @return 如果找到对应的用户则返回这个用户的数据，如果没有找到则返回null值
    *
    * */
    User findByUsername(String username);

    Integer updatePasswordByUid(Integer uid, String password, String modifiedUser, Date modifiedTime);

    User findByUid(Integer uid);

    Integer updateInfoByUid(User user);

    Integer updateAvatarByUid(Integer uid,String avatar, String modifiedUser, Date modifiedTime);
}