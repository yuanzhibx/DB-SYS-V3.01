package com.cy.pj.sys.dao;

import com.cy.pj.sys.entity.SysUserDept;
import org.apache.ibatis.annotations.Mapper;

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

}
