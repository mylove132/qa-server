package com.okjiaoyu.auto.service.impl;

import com.github.pagehelper.PageHelper;
import com.okjiaoyu.auto.dao.UserEntityMapper;
import com.okjiaoyu.auto.service.IUserService;
import com.okjiaoyu.auto.vo.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-06-28:16:22
 * Modify date: 2019-06-28:16:22
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserEntityMapper userMapper;

    @Transactional
    @Override
    public int deleteUserById(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Transactional
    @Override
    public int addUser(UserEntity user) {
        return userMapper.insertSelective(user);
    }

    @Override
    public List<UserEntity> getUserList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return userMapper.queryUserList();
    }

    @Override
    public UserEntity getUserById(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public UserEntity getUserByEmail(String email) {
        return userMapper.selectByEmail(email);
    }

    @Override
    public UserEntity getUserByAccount(String account) {
        return userMapper.selectByAccount(account);
    }


    @Transactional
    @Override
    public int updateUser(UserEntity user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }
}
