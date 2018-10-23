package org.shiro.dao;

import org.shiro.entity.Role;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);
    List<String> RolesByUserName(String username);
    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}