package com.cy.pj.sys.service.impl;

import com.cy.pj.common.bo.Node;
import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.sys.dao.SysMenuDao;
import com.cy.pj.sys.dao.SysRoleMenuDao;
import com.cy.pj.sys.entity.SysMenu;
import com.cy.pj.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 菜单管理模块业务层实现类
 *
 * @author Yuanzhibx
 * @Date 2020-06-15
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuDao sysMenuDao;
    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;

    @Override
    public List<Map<String, Object>> findObjects() {
        List<Map<String, Object>> list = sysMenuDao.findObjects();
        return list;
    }

    /**
     * 基于菜单 id 删除 菜单记录 以及 相关的菜单关系数据
     * @param id 要删除记录的 id
     * @return rows 删除的行数
     */
    @Override
    public int deleteObject(Integer id) {
        //1. 验证数据的合法性
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("请先选择记录");
        }
        //2. 基于 id 进行子元素查询
        int count = sysMenuDao.getChildCount(id);
        if (count > 0) {
            throw new ServiceException("请先删除子菜单");
        }
        //3. 删除菜单关系数据
        sysRoleMenuDao.deleteObjectsByMenuId(id);
        //4. 删除菜单元素
        int rows = sysMenuDao.deleteObject(id);
        if (rows == 0) {
            throw new ServiceException("此菜单已经不存在");
        }
        //5. 返回结果
        return rows;

    }

    /**
     * 查询所有菜单的 id, name, parentId 信息
     * @return
     */
    @Override
    public List<Node> findZtreeMenuNodes() {
        return sysMenuDao.findZtreeMenuNodes();
    }

    /**
     * 实现新增菜单保存操作
     * @param entity
     * @return
     */
    @Override
    public int saveObject(SysMenu entity) {
        //1. 合法验证
        if (entity == null) {
            throw new ServiceException("保存对象不能为空");
        }
        //2. 保存数据
        int rows = sysMenuDao.insertObject(entity);
        //3. 返回数据
        return rows;
    }

    /**
     * 实现更新菜单保存操作
     * @param entity
     * @return
     */
    @Override
    public int updateObject(SysMenu entity) {
        int rows = sysMenuDao.updateObject(entity);
        return rows;
    }
}
