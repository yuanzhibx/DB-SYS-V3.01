package com.cy.pj.sys.dao;

import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.sys.entity.SysUserDept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户管理模块数据层接口
 * sys_users
 *
 * @author Yuanzhibx
 * @Date 2020-06-18
 */
@Mapper
public interface SysUserDao {

    /**
     * 根据查询条件统计记录总数
     * @param username
     * @return
     */
//    int getRowCount(String username);

    /**
     * 基于查询条件查询当前页的记录
     * @param username
     * @return
     */
    List<SysUserDept> findPageObjects(String username);

    /**
     * 禁用或启用用户信息
     * @param id
     * @param valid
     * @param modifiedUser
     * @return
     */
    int validById(Integer id, Integer valid, String modifiedUser);

    /**
     * 新增
     * 将 [新增] 的用户数据持久化到数据库
     * @param entity
     * @return
     */
    int insertObject(SysUser entity);

    /**
     * 基于用户 id 查询用户信息
     * @param id
     * @return
     */
    SysUserDept findObjectById(Integer id);

    /**
     * 更新用户数据
     * @param entity
     * @return
     */
    int updateObject(SysUser entity);

    /**
     * 修改密码
     * @param password
     * @param salt
     * @param id
     * @return
     */
    int updatePassword(@Param("password") String password, @Param("salt") String salt, @Param("id") Integer id);
}
