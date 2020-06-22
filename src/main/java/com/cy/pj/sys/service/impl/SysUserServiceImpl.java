package com.cy.pj.sys.service.impl;

import com.cy.pj.common.annotation.RequiredLog;
import com.cy.pj.common.bo.PageObject;
import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.sys.dao.SysUserDao;
import com.cy.pj.sys.dao.SysUserRoleDao;
import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.sys.entity.SysUserDept;
import com.cy.pj.sys.service.SysUserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 用户管理模块业务层实现类
 *
 * @author Yuanzhibx
 * @Date 2020-06-18
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private SysUserRoleDao sysUserRoleDao;

    /**
     * 实现用户管理的分页查询业务
     * 使用 PageHelper 实现
     *
     * @param username    用户名(基于条件查询时的参数名  数据最终来源为 client)
     * @param pageCurrent 当前的页码值(数据最终来源为 client)
     * @return
     */
    @RequiredLog(operation = "分页查询")
    @Override
    public PageObject<SysUserDept> findPageObjects(String username, Integer pageCurrent) {
        //1. 对参数进行校验
        if (pageCurrent == null || pageCurrent < 1) {
            throw new IllegalArgumentException("当前页码值无效");
        }
        //2. 设置分页参数
        Page<Object> page = PageHelper.startPage(pageCurrent, 3);
        //3. 查询当前页记录
        List<SysUserDept> records = sysUserDao.findPageObjects(username);
        return new PageObject(pageCurrent, page.getPageSize(), (int) page.getTotal(), records);
    }

    /**
     * 修改用户状态
     * @param id 用户的 id
     * @param valid 状态信息(0, 1)
     * @return
     */
    @Override
    public int validById(Integer id, Integer valid) {
        //1. 参数校验
        if (id == null || id <= 0) {
            throw new ServiceException("ID[id] 参数不合法");
        }
        if (valid != 1 && valid != 0) {
            throw new ServiceException("状态[valid] 参数不合法");
        }
        //2. 执行修改用户状态
        int rows = sysUserDao.validById(id, valid, "Yuanzhibx");
        //3. 判定结果
        if (rows == 0) {
            throw new ServiceException("此记录已经不存在");
        }
        return rows;
    }

    /**
     * 保存 [新增] 的 用户数据 用户角色关系数据
     * @param entity 用户数据
     * @param roleIds 用户对应的角色 id
     * @return
     */
    @Override
    public int saveObject(SysUser entity, Integer[] roleIds) {
        //1. 参数校验
        if (entity == null) {
            throw new IllegalArgumentException("保存对象不能为空");
        }
        if (StringUtils.isEmpty(entity.getUsername())) {
            throw new IllegalArgumentException("用户名不能为空");
        }
        if (StringUtils.isEmpty(entity.getPassword())) {
            throw new IllegalArgumentException("密码不能为空");
        }
        if (roleIds == null || roleIds.length == 0) {
            throw new IllegalArgumentException("请为用户分配角色");
        }
        //2. 对密码进行加密
        /*
            SimpleHash
                algorithmName 算法名称
                source 要加密的密码
                salt 加密盐
                hashIterations 加密次数
         */
        String salt = UUID.randomUUID().toString();
        SimpleHash simpleHash = new SimpleHash("MD5", entity.getPassword(), salt, 1);
        String password = simpleHash.toHex();
        int rows = sysUserDao.insertObject(entity);
        //3. 将用户信息写入数据库
        entity.setSalt(salt);
        entity.setPassword(password);
        //4. 将用户对应的角色信息写入到数据库
        sysUserRoleDao.insertObjects(entity.getId(), roleIds);
        return rows;
    }

    /**
     * 基于用户 id 查询用户及相关信息
     * @param userId 用户 id
     * @return
     */
    @Override
    public Map<String, Object> findObjectById(Integer userId) {
        //1. 参数校验
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("[userId] 参数不合法");
        }
        //2. 业务查询
        SysUserDept user = sysUserDao.findObjectById(userId);
        if (user == null) {
            throw new IllegalArgumentException("此用户不存在");
        }
        List<Integer> roleIds = sysUserRoleDao.findRoleIdsByUserId(userId);
        //3. 数据封装
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        map.put("roleIds", roleIds);
        return map;
    }

    /**
     * 更新用户数据 及 用户角色关系数据
     * @param entity 用户数据
     * @param roleIds 用户对应的角色 id
     * @return
     */
    @Override
    public int updateObject(SysUser entity, Integer[] roleIds) {
        //1. 参数校验
        if (entity == null) {
            throw new IllegalArgumentException("保存对象不能为空");
        }
        if (StringUtils.isEmpty(entity.getUsername())) {
            throw new IllegalArgumentException("用户名不能为空");
        }
        if (roleIds == null || roleIds.length == 0) {
            throw new IllegalArgumentException("请为其指定角色");
        }
        //2. 更新用户信息
        int rows = sysUserDao.updateObject(entity);
        //3. 更新用户与角色关系数据
        sysUserRoleDao.deleteObjectsByUserId(entity.getId());
        sysUserRoleDao.insertObjects(entity.getId(), roleIds);
        if (rows == 0) {
            throw new ServiceException("记录不存在");
        }
        return rows;
    }

    /**
     * 修改用户密码
     * @param password 原密码
     * @param newPassword 新密码
     * @param cfgPassword 确认新密码
     * @return
     */
//    @Override
//    public int updatePassword(String password, String newPassword, String cfgPassword) {
//        //1. 参数校验
//        if (StringUtils.isEmpty(newPassword)) {
//            throw new IllegalArgumentException("新密码不能为空");
//        }
//        if (StringUtils.isEmpty(cfgPassword)) {
//            throw new IllegalArgumentException("确认密码不能为空");
//        }
//        return 0;
//    }

}
