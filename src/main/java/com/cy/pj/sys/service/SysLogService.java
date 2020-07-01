package com.cy.pj.sys.service;

import com.cy.pj.common.bo.PageObject;
import com.cy.pj.sys.entity.SysLog;
import org.apache.ibatis.annotations.Param;

/**
 * 日志模块业务层接口
 * 负责定义日志业务模块规则
 *      1. 查询日志业务(添加分页业务)
 *      2. 删除日志业务(权限控制)
 *      3. 添加日志业务(AOP)
 *
 * @author Yuanzhibx
 * @Date 2020-06-10
 */
public interface SysLogService {

    /**
     * 定义日志的分页查询业务
     * @param username 用户名(基于条件查询时的参数名  数据最终来源为 client)
     * @param pageCurrent 当前的页码值(数据最终来源为 client)
     * @return 当前页记录 + 分页信息的对象(PageObject)
     */
    PageObject<SysLog> findPageObjects(String username, Integer pageCurrent);//阿里规范 listObject

    /**
     * 基于日志记录 id 删除日志
     * @param ids 要删除的 id
     * @return
     */
    int deleteObjects(@Param("ids") Integer... ids);

    /**
     * 保存日志信息
     * @param entity
     */
    void saveObject(SysLog entity);
}
