package com.cy.pj.sys.dao;

import com.cy.pj.common.bo.Node;
import com.cy.pj.sys.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 菜单管理模块数据层接口
 * sys_menus
 *
 * @author Yuanzhibx
 * @Date 2020-06-15
 */
@Mapper
public interface SysMenuDao {

    /**
     * 实现菜单数据的显示
     * @return
     */
    List<Map<String, Object>> findObjects();
//    List<SysMenu> findObjects();

    /**
     * 基于菜单 id 查询子菜单记录数量
     * @param id
     * @return
     */
    int getChildCount(Integer id);

    /**
     * 基于菜单 id 删除菜单记录
     * @param id
     * @return
     */
    int deleteObject(Integer id);

    /**
     * 查询所有菜单的 id, name, parentId 信息
     * @return
     */
    List<Node> findZtreeMenuNodes();

    /**
     * 新增
     * 将用户 [新增] 的菜单数据持久化到数据库
     * @param entity
     * @return
     */
    int insertObject(SysMenu entity);

    /**
     * 更新
     * 将用户 [更新] 的数据持久化到数据库
     * @param entity
     * @return
     */
    int updateObject(SysMenu entity);

}
