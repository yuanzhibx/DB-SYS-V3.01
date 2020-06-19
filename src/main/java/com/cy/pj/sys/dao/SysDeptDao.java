package com.cy.pj.sys.dao;

import com.cy.pj.common.bo.Node;
import com.cy.pj.sys.entity.SysDept;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

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

    @Select("select c.*,p.name parentName from sys_depts c left join sys_depts p on c.parentId=p.id")
    List<Map<String,Object>> findObjects();

    @Select("select id,name,parentId from sys_depts")
    List<Node> findZTreeNodes();

    int updateObject(SysDept entity);

    int insertObject(SysDept entity);

    @Select("select count(*) from sys_depts where parentId=#{id}")
    int getChildCount(Integer id);

    @Delete("delete from sys_depts where id=#{id}")
    int deleteObject(Integer id);

}
