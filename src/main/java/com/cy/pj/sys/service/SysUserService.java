package com.cy.pj.sys.service;

import com.cy.pj.common.bo.PageObject;
import com.cy.pj.sys.entity.SysUserDept;

/**
 * @author Yuanzhibx
 * @Date 2020-06-18
 */
public interface SysUserService {

    PageObject<SysUserDept> findPageObjects(String username, Integer pageCurrent);

}
