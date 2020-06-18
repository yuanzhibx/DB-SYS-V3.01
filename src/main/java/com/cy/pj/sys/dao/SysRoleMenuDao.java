package com.cy.pj.sys.dao;

import com.cy.pj.sys.entity.SysRoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 菜单管理模块数据层接口
 * 角色管理模块数据层接口
 *
 * sys_roles_menus
 *
 * @author Yuanzhibx
 * @Date 2020-06-15
 */
@Mapper
public interface SysRoleMenuDao {

    /**
     * 基于菜单 id 删除菜单角色关系数据
     * @param menuId 菜单 id
     * @return 影响行数
     */
    int deleteObjectsByMenuId(Integer menuId);

    /**
     * 基于角色 id 删除角色菜单关系数据
     * @param roleId
     * @return
     */
    int deleteObjectsByRoleId(Integer roleId);

    /**
     * 新增
     * 将用户 [新增] 的角色关系数据持久化到数据库
     * @param roleId
     * @param menuIds
     * @return
     */
    int insertObjects(@Param("roleId")Integer roleId, @Param("menuIds") Integer[] menuIds);

    /**
     * 基于 id 查询角色菜单关系数据
     * @param id
     * @return
     */
    SysRoleMenu findMenuIdsByRoleId(Integer id);

}
