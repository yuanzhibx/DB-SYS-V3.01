package com.cy.pj.sys.service.impl;

import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.bo.PageObject;
import com.cy.pj.sys.dao.SysLogDao;
import com.cy.pj.sys.entity.SysLog;
import com.cy.pj.sys.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 日志模块业务层实现类
 *
 * @author Yuanzhibx
 * @Date 2020-06-10
 */
@Service
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogDao sysLogDao;

    @Override
    public PageObject<SysLog> findPageObjects(String username, Integer pageCurrent) {
        //1. 参数校验
        // username 允许为空
        if (pageCurrent == null || pageCurrent < 1) {
            throw new IllegalArgumentException("当前页码值不合法");
        }
        //2. 查询总记录数, 并进行校验
        //空指针异常则为 dao 遍历为空
        int rowCount = sysLogDao.getRowCount(username);
        //查询记录为空
        if (rowCount == 0) {
            throw new ServiceException("没有对应记录");
        }
        //3. 查询当前页记录
        //每页最多要显示的记录数
        int pageSize = 30;
        //计算当前页查询的起始位置  (前面页面之和 * 每页行数)
        int startIndex = (pageCurrent - 1) * pageSize;
        List<SysLog> resords = sysLogDao.findPageObjects(username, startIndex, pageSize);
        //4. 对业务层查询结果进行处理和封装
        // PageObject 构造方法传参的顺序由构造方法定义时参数的顺序决定
        return new PageObject<>(pageCurrent, pageSize, rowCount,resords);
    }

    @Override
    public int deleteObjects(Integer... ids) {
        //1. 判断参数合法性
        if (ids == null || ids.length == 0) {
            throw new IllegalArgumentException("参数值无效");
        }
        //2. 执行删除操作
        int rows = sysLogDao.deleteObjects(ids);
        //3. 对结果进行验证
        if (rows == 0) {
            throw new ServiceException("该记录已不存在");
        }
        return rows;
    }

    @Override
    public void saveObject(SysLog entity) {
        sysLogDao.insertObject(entity);
    }
}
