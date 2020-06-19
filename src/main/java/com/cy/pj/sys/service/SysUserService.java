package com.cy.pj.sys.service;

import com.cy.pj.common.bo.PageObject;
import com.cy.pj.sys.entity.SysUserDept;

/**
 * 用户管理模块业务层接口
 * 定义用户业务接口及方法
 *
 * @author Yuanzhibx
 * @Date 2020-06-18
 */
public interface SysUserService {

    /**
     * 定义角色管理的分页查询业务
     * @param username 用户名(基于条件查询时的参数名  数据最终来源为 client)
     * @param pageCurrent 当前的页码值(数据最终来源为 client)
     * @return
     */
    PageObject<SysUserDept> findPageObjects(String username, Integer pageCurrent);

}
