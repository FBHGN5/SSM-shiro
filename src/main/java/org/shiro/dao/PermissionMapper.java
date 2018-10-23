package org.shiro.dao;

import org.shiro.entity.Permission;

import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer id);
    List<String> findPermissionByUsername(String username);
    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
}