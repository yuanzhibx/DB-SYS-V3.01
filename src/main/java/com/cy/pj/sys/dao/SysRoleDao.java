package com.cy.pj.sys.dao;

import com.cy.pj.common.bo.CheckBox;
import com.cy.pj.sys.entity.SysRole;
import com.cy.pj.sys.entity.SysRoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 角色管理模块数据层接口
 * sys_roles
 *
 * @author Yuanzhibx
 * @Date 2020-06-16
 */
@Mapper
public interface SysRoleDao {

    /**
     * 根据查询条件统计记录总数
     * @param name 查询条件(角色名)
     * @return 查询到的记录总数
     */
    int getRowCount(@Param("name") String name);

    /**
     * 基于查询条件查询当前页的记录
     * @param name 查询条件
     * @param startIndex 当前页的起始位置
     * @param pageSize 当前页的页面大小
     * @return 当前页的日志记录信息
     */
    List<SysRole> findPageObjects(@Param("name")String name, @Param("startIndex")Integer startIndex, @Param("pageSize")Integer pageSize);
//    List<SysRole> findPageObjects(String name);

    /**
     * 基于 id 删除角色记录
     * @param id
     * @return
     */
    int deleteObject(Integer id);

    /**
     * 新增
     * 将用户 [新增] 的角色数据持久化到数据库
     * @param entity
     * @return
     */
    int insertObject(SysRole entity);

    /**
     * 基于 id 查询角色数据
     * @param id
     * @return
     */
    SysRoleMenu findObjectById(Integer id);

    /**
     * 更新
     * 将用户 [更新] 的角色数据持久化到数据库
     * @param entity
     * @return
     */
    int updateObject(SysRole entity);

    /**
     * 查询角色 id, name
     * @return
     */
    List<CheckBox> findObjects();

}
