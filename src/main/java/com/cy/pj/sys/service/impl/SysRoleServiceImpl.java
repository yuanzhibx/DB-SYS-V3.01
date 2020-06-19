package com.cy.pj.sys.service.impl;

import com.cy.pj.common.bo.CheckBox;
import com.cy.pj.common.bo.PageObject;
import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.sys.dao.SysRoleDao;
import com.cy.pj.sys.dao.SysRoleMenuDao;
import com.cy.pj.sys.dao.SysUserRoleDao;
import com.cy.pj.sys.entity.SysRole;
import com.cy.pj.sys.entity.SysRoleMenu;
import com.cy.pj.sys.service.SysRoleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 角色管理模块业务层实现类
 *
 * @author Yuanzhibx
 * @Date 2020-06-16
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleDao sysRoleDao;

    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;

    @Autowired
    private SysUserRoleDao sysUserRoleDao;

    /**
     * 实现角色管理的分页查询业务
     * @param name 用户名(基于条件查询时的参数名  数据最终来源为 client)
     * @param pageCurrent 当前的页码值(数据最终来源为 client)
     * @return
     */
    @Override
    public PageObject<SysRole> findPageObjects(String name, Integer pageCurrent) {
        //1. 对参数进行校验
        if (pageCurrent == null || pageCurrent < 1) {
            throw new IllegalArgumentException("当前页码值" + pageCurrent + "不存在");
        }
        //2. 查询总记录数并进行校验
        int rowCount = sysRoleDao.getRowCount(name);
        if (rowCount == 0) {
            throw new ServiceException("没有记录...");
        }
        //3. 查询当前页记录
        int pageSize = 2;
        int startIndex = (pageCurrent-1) * pageSize;
        List<SysRole> records = sysRoleDao.findPageObjects(name, startIndex, pageSize);

        //4. 对查询结果进行封装并进行返回
        return new PageObject<>(pageCurrent, pageSize, rowCount, records);
    }

//    @Override
//    public PageObject<SysRole> findPageObjects(String name, Integer pageCurrent) {
//        //1. 对参数进行校验
//        if (pageCurrent == null || pageCurrent < 1) {
//            throw new IllegalArgumentException("当前页码值" + pageCurrent + "不存在");
//        }
//        //2. 查询当前页记录
//        //2.1. 设置查询参数
//        int pageSize = 2;
//        Page<SysRole> page = PageHelper.startPage(pageCurrent, pageSize);
//        //2.2. 查询
//        List<SysRole> records = sysRoleDao.findPageObjects(name);
//
//        //3. 对查询结果进行封装并进行返回
//        return new PageObject<>(pageCurrent, pageSize, (int)page.getTotal(), (int)page.getPages(), records);
//    }

    /**
     * 基于菜单 id 删除 菜单记录 以及 相关的菜单关系数据
     * @param id
     * @return
     */
    @Override
    public int deleteObject(Integer id) {
        //1. 验证数据的合法性
        if(id == null || id <= 0) {
            throw new IllegalArgumentException("请先选择");
        }
        //2. 基于 id 删除关系数据
        sysRoleMenuDao.deleteObjectsByRoleId(id);
        sysUserRoleDao.deleteObjectsByRoleId(id);
        //3. 删除角色数据
        int rows = sysRoleDao.deleteObject(id);
        if(rows == 0) {
            throw new ServiceException("此记录可能已经不存在");
        }
        //4.  返回结果
        return rows;
    }

    /**
     * 保存角色数据 保存角色和菜单关系数据
     * @param entity
     * @param menuIds
     * @return
     */
    @Override
    public int saveObject(SysRole entity, Integer[] menuIds) {
        //1. 验证数据的合法性
        if (entity == null) {
            throw new IllegalArgumentException("保存对象不能为空");
        }
        if (StringUtils.isEmpty(entity.getName())) {
            throw new IllegalArgumentException("角色名不允许为空");
        }
        if (menuIds == null || menuIds.length == 0) {
            throw new ServiceException("必须为角色分配权限");
        }
        //2. 保存角色自身信息
        int rows = sysRoleDao.insertObject(entity);
        //3. 保存角色和菜单关系数据
        sysRoleMenuDao.insertObjects(entity.getId(), menuIds);
        return rows;
    }

    /**
     * 基于角色 id 查询角色信息 以及 相关的角色关系数据
     * @param id
     * @return
     */
    @Override
    public SysRoleMenu findObjectById(Integer id) {
        //1. 验证数据的合法性
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("保存对象不能为空");
        }
        //2. 查询
        SysRoleMenu result = sysRoleDao.findObjectById(id);
        //3. 验证结果
        if (result == null) {
            throw new ServiceException("记录不存在");
        }
        return  result;
    }

    /**
     * 保存更新的角色数据 保存角色和菜单关系数据
     * @param entity
     * @param menuIds
     * @return
     */
    @Override
    public int updateObject(SysRole entity, Integer[] menuIds) {
        //1. 验证数据的合法性
        if (entity == null) {
            throw new IllegalArgumentException("更新的对象不能为空哦");
        }
        if (entity.getId() == null) {
            throw new IllegalArgumentException("更新对象的 id 值不能为空");
        }
        if (StringUtils.isEmpty(entity.getName())) {
            throw new IllegalArgumentException("角色名不能为空");
        }
        if (menuIds == null || menuIds.length == 0) {
            throw new IllegalArgumentException("请为用户指定权限");
        }
        //2. 更新数据
        int rows = sysRoleDao.updateObject(entity);
        //3. 验证结果
        if (rows == 0) {
            throw new ServiceException("对象可能已经不存在");
        }
        //更新角色菜单关联数据
        sysRoleMenuDao.deleteObjectsByMenuId(entity.getId());
        sysRoleMenuDao.insertObjects(entity.getId(), menuIds);
        return rows;
    }

    @Override
    public List<CheckBox> findObjects() {
        List<CheckBox> objects = sysRoleDao.findObjects();
        return objects;
    }

}
