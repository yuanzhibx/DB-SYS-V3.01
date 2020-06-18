package com.cy.pj.sys.service;

import com.cy.pj.common.bo.PageObject;
import com.cy.pj.sys.entity.SysRole;
import com.cy.pj.sys.entity.SysRoleMenu;
import org.apache.ibatis.annotations.Param;

/**
 * 角色管理模块业务层接口
 * 定义角色业务接口及方法
 *
 * @author Yuanzhibx
 * @Date 2020-06-16
 */
public interface SysRoleService {

    /**
     * 定义角色管理的分页查询业务
     * @param name 用户名(基于条件查询时的参数名  数据最终来源为 client)
     * @param pageCurrent 当前的页码值(数据最终来源为 client)
     * @return 当前页记录 + 分页信息的对象(PageObject)
     */
    PageObject<SysRole> findPageObjects(String name, Integer pageCurrent);

    /**
     * 基于角色 id 删除 角色记录 以及 相关的角色关系数据
     * @param id
     * @return
     */
    int deleteObject(Integer id);

    /**
     * 新增
     * 保存角色数据 保存角色和菜单关系数据
     * @param entity
     * @param menuIds
     * @return
     */
    int saveObject(SysRole entity, Integer[] menuIds);

    /**
     * 基于角色 id 查询角色信息 以及 相关的角色关系数据
     * @param id
     * @return
     */
    SysRoleMenu findObjectById(Integer id);

    /**
     * 更新
     * 保存更新的角色数据 保存角色和菜单关系数据
     * @param entity
     * @param menuIds
     * @return
     */
    int updateObject(SysRole entity, Integer[] menuIds);
}
