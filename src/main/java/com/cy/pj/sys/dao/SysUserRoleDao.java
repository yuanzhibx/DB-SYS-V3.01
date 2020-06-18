package com.cy.pj.sys.dao;

import org.apache.ibatis.annotations.Mapper;

/**
 * 角色管理模块数据层接口
 * sys_user_roles
 *
 * @author Yuanzhibx
 * @Date 2020-06-16
 */
@Mapper
public interface SysUserRoleDao {

    /**
     * 基于角色 id 删除角色用户关系记录
     * @param roleId
     * @return
     */
    int deleteObjectsByRoleId(Integer roleId);

}
