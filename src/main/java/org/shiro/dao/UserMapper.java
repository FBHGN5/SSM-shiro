package org.shiro.dao;

import org.shiro.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);
    User findByUsername(String username);
    int updateByPrimaryKeySelective(User record);
    int updateByPrimaryKey(User record);
}