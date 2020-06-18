package com.cy.pj.sys.service.impl;

import com.cy.pj.common.bo.PageObject;
import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.sys.dao.SysUserDao;
import com.cy.pj.sys.entity.SysUserDept;
import com.cy.pj.sys.service.SysUserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Yuanzhibx
 * @Date 2020-06-18
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public PageObject<SysUserDept> findPageObjects(String username, Integer pageCurrent) {
        //1. 对参数进行校验
        if (pageCurrent == null || pageCurrent < 1) {
            throw new IllegalArgumentException("当前页码值无效");
        }
        Page<Object> page = PageHelper.startPage(pageCurrent, 3);
        List<SysUserDept> records = sysUserDao.findPageObjects(username);
        return new PageObject(pageCurrent, page.getPageSize(), (int) page.getTotal(), page.getPageSize(), records);
    }
}
