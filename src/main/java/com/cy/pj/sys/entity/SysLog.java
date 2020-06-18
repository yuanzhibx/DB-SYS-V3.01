package com.cy.pj.sys.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 日志实体对象
 * 基于此对象存储表中查询到的数据
 * 用于对 sys_logs 表数据进行封装
 *
 * 建议:
 *      在 java 中所有用于存储数据的对象都实现 Serializable 接口 (序列化接口)
 *
 * @author Yuanzhibx
 * @Date 2020-06-10
 */
@Getter
@Setter
public class SysLog implements Serializable {

    private static final long serialVersionUID = 2552108194030970453L;

    /**
     * id
     */
    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户操作
     */
    private String operation;
    /**
     * 请求方法
     */
    private String method;
    /**
     * 请求参数
     */
    private String params;
    /**
     * 执行时长(毫秒)
     */
    private Long time;
    /**
     * IP地址
     */
    private String ip;
    /**
     * 创建时间
     */
    private Date createdTime;

}
