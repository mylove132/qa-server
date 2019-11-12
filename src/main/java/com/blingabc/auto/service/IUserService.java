package com.blingabc.auto.service;


import com.blingabc.auto.vo.UserEntity;

import java.util.List;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-06-28:16:08
 * Modify date: 2019-06-28:16:08
 */
public interface IUserService {

    int deleteUserById(Integer id);

    int addUser(UserEntity user);

    List<UserEntity> getUserList(Integer pageNum, Integer pageSize);

    UserEntity getUserById(Integer userId);

    UserEntity getUserByEmail(String email);

    UserEntity getUserByAccount(String account);

    int updateUser(UserEntity user);


}
