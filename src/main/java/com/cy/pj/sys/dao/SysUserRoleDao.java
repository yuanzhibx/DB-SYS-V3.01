package com.cy.pj.sys.dao;

import com.cy.pj.sys.entity.SysUserDept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    /**
     * 新增 & 更新
     * 将 [新增] 的用户角色关系数据持久化到数据库
     * 将 [更新] 的用户角色关系数据持久化到数据库
     * @param userId
     * @param roleIds
     * @return
     */
    int insertObjects(@Param("userId") Integer userId, @Param("roleIds") Integer[] roleIds);

    /**
     * 基于用户 id 查询 角色 id 数据
     * @param id
     * @return
     */
    List<Integer> findRoleIdsByUserId(Integer id);

    /**
     * 基于用户 id 删除用户关系数据
     * @param userId
     * @return
     */
    int deleteObjectsByUserId(Integer userId);

}
