package com.cy.pj.sys.service;

import com.cy.pj.common.bo.Node;
import com.cy.pj.sys.entity.SysMenu;

import java.util.List;
import java.util.Map;

/**
 * 菜单管理模块业务层接口
 *
 * @author Yuanzhibx
 * @Date 2020-06-15
 */
public interface SysMenuService {

    /**
     * 实现菜单数据的显示
     * @return
     */
    List<Map<String, Object>> findObjects();
//    List<SysMenu> findObjects();

    /**
     * 基于菜单 id 删除 菜单记录 以及 相关的菜单关系数据
     * @param id 要删除的 id
     * @return
     */
    int deleteObject(Integer id);

    /**
     * 查询所有菜单的 id, name, parentId 信息
     * @return
     */
    List<Node> findZtreeMenuNodes();

    /**
     * 实现新增菜单保存操作
     * @param entity
     * @return
     */
    int saveObject(SysMenu entity);

    /**
     * 实现更新菜单保存操作
     * @param entity
     * @return
     */
    int updateObject(SysMenu entity);
}
