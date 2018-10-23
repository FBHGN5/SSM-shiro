package org.shiro.service.Impl;

import org.shiro.dao.UserMapper;
import org.shiro.entity.User;
import org.shiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User login() {
        System.out.println(userMapper.selectByPrimaryKey(1));
        return null;
    }
}
