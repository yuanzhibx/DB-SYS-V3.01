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
 * 用户管理模块业务层实现类
 *
 * @author Yuanzhibx
 * @Date 2020-06-18
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserDao sysUserDao;

    /**
     * 实现用户管理的分页查询业务
     * 使用 PageHelper 实现
     * @param username 用户名(基于条件查询时的参数名  数据最终来源为 client)
     * @param pageCurrent 当前的页码值(数据最终来源为 client)
     * @return
     */
    @Override
    public PageObject<SysUserDept> findPageObjects(String username, Integer pageCurrent) {
        //1. 对参数进行校验
        if (pageCurrent == null || pageCurrent < 1) {
            throw new IllegalArgumentException("当前页码值无效");
        }
        //2. 查询数据
        Page<Object> page = PageHelper.startPage(pageCurrent, 3);
        List<SysUserDept> records = sysUserDao.findPageObjects(username);
        return new PageObject(pageCurrent, page.getPageSize(), (int) page.getTotal(), page.getPageSize(), records);
    }

}
