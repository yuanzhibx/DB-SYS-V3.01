package com.cy.pj.sys.dao;

import com.cy.pj.sys.entity.SysDept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author Yuanzhibx
 * @Date 2020-06-18
 */
@Mapper
public interface SysDeptDao {

    SysDept findById(Integer id);
}
