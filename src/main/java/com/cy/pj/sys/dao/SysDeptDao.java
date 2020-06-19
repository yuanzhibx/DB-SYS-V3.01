package com.cy.pj.sys.dao;

import com.cy.pj.sys.entity.SysDept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 用户管理模块数据层接口
 * sys_depts
 *
 * @author Yuanzhibx
 * @Date 2020-06-18
 */
@Mapper
public interface SysDeptDao {

    /**
     * 根据 id 查询部门数据
     * @param id
     * @return
     */
    SysDept findById(Integer id);

}
