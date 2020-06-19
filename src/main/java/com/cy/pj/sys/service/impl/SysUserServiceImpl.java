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
import org.springframework.util.StringUtils;

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
     *
     * @param username    用户名(基于条件查询时的参数名  数据最终来源为 client)
     * @param pageCurrent 当前的页码值(数据最终来源为 client)
     * @return
     */
    @Override
    public PageObject<SysUserDept> findPageObjects(String username, Integer pageCurrent) {
        //1. 对参数进行校验
        if (pageCurrent == null || pageCurrent < 1) {
            throw new IllegalArgumentException("当前页码值无效");
        }
        //2. 设置分页参数
        Page<Object> page = PageHelper.startPage(pageCurrent, 3);
        //3. 查询当前页记录
        List<SysUserDept> records = sysUserDao.findPageObjects(username);
        return new PageObject(pageCurrent, page.getPageSize(), (int) page.getTotal(), page.getPageSize(), records);
    }

    /**
     * 修改用户状态
     * @param id 用户的 id
     * @param valid 状态信息(0, 1)
     * @return
     */
    @Override
    public int validById(Integer id, Integer valid) {
        //1. 参数校验
        if (id == null || id <= 0) {
            throw new ServiceException("ID[id] 参数不合法");
        }
        if (valid != 1 && valid != 0) {
            throw new ServiceException("状态[valid] 参数不合法");
        }
        //2. 执行修改用户状态
        int rows = sysUserDao.validById(id, valid, "Yuanzhibx");
        //3. 判定结果
        if (rows == 0) {
            throw new ServiceException("此记录已经不存在");
        }
        return rows;
    }

}
