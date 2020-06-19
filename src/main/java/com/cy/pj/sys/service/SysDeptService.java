package com.cy.pj.sys.service;

import com.cy.pj.common.bo.Node;
import com.cy.pj.sys.entity.SysDept;

import java.util.List;
import java.util.Map;

/**
 * @author Yuanzhibx
 * @Date 2020-06-19
 */
public interface SysDeptService {

    List<Map<String,Object>> findObjects();

    List<Node> findZTreeNodes();

    int saveObject(SysDept entity);

    int updateObject(SysDept entity);

    int deleteObject(Integer id);

}
