package com.cy.pj.sys.dao;

import com.cy.pj.sys.entity.SysLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 日志模块数据层接口
 * 定义数据访问规范
 *
 * @author Yuanzhibx
 * @Date 2020-06-10
 */
@Mapper
public interface SysLogDao {

    /**
     * 基于查询条件统计记录总数
     * @param username 查询条件
     * @return 查询到的记录总数
     */
    int getRowCount(String username);

    /**
     * 基于查询条件查询当前页的记录
     * @param username  查询条件(例如查询哪个用户的日志信息)
     * @param startIndex 当前页的起始位置(前面页面之和 * 每页行数)
     * @param pageSize 当前页的页面大小(每页最多显示记录条数)
     * @return 当前页的日志记录信息
     */
    List<SysLog> findPageObjects(String username, Integer startIndex, Integer pageSize);

    /**
     * 基于日志记录 id 删除日志
     * @param ids 要删除的日志记录 id, 未使用 @Param 注解进行参数描述, 则
     * @return
     */
    int deleteObjects(@Param("ids") Integer... ids);

}
