package com.cy.pj.sys.service;

import com.cy.pj.common.bo.PageObject;
import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.sys.entity.SysUserDept;

import java.util.Map;

/**
 * 用户管理模块业务层接口
 * 定义用户业务接口及方法
 *
 * @author Yuanzhibx
 * @Date 2020-06-18
 */
public interface SysUserService {

    /**
     * 定义角色管理的分页查询业务
     *
     * @param username    用户名(基于条件查询时的参数名  数据最终来源为 client)
     * @param pageCurrent 当前的页码值(数据最终来源为 client)
     * @return
     */
    PageObject<SysUserDept> findPageObjects(String username, Integer pageCurrent);

    /**
     * 修改用户状态
     * @param id 用户的 id
     * @param valid 状态信息(0, 1)
     * @return
     */
    int validById(Integer id, Integer valid);

    /**
     * 保存 [新增] 的 用户数据 用户角色关系数据
     * @param entity 用户数据
     * @param roleIds 用户对应的角色 id
     * @return
     */
    int saveObject(SysUser entity, Integer[] roleIds);

    /**
     * 基于用户 id 查询用户及相关信息
     * @param userId 用户 id
     * @return
     */
    Map<String, Object> findObjectById(Integer userId);

    /**
     * 更新用户数据 及 用户角色关系数据
     * @param entity 用户数据
     * @param roleIds 用户对应的角色 id
     * @return
     */
    int updateObject(SysUser entity, Integer[] roleIds);

    /**
     * 修改用户密码
     * @param password 原密码
     * @param newPassword 新密码
     * @param cfgPassword 确认新密码
     * @return
     */
//    int updatePassword(String password, String newPassword, String cfgPassword);
}
