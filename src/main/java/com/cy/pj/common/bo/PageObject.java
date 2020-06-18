package com.cy.pj.common.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * POJO(VO/BO/DTO/DO) 中的 BO 对象
 *      1. 所有的 POJO 对象属性定义都用对象类型
 *      2. 所有的 POJO 对象属性默认值无需指定
 *      3. 所有的 POJO 对象需要提供 set/get/toString 方法, 对 boolean 提供 isXX 方法
 *      4. 所有的 POJO 对象都需要提供无参构造函数
 *      5. 所有的 POJO 对象的构造方法不要写任何业务逻辑
 *
 * 业务层对象 (Value Object)
 * 此对象为业务层向外输出的一个 BO 对象, 用于封装业务执行的结束
 * 基于此对象封装数据层返回的数据以及计算的分页信息
 *
 * 泛型:
 *      类上定义的泛型用于约束类中属性, 方法属性, 方法返回值类型
 *
 * @author Yuanzhibx
 * @Date 2020-06-11
 */
@Data
@NoArgsConstructor
public class PageObject<T> implements Serializable {

    private static final long serialVersionUID = 1536631092048817285L;

    /**
     * 当前页的页码值
     */
    private Integer pageCurrent = 1;

    /**
     * 页面大小
     * 每页要显示多少条记录
     */
    private Integer pageSize = 3;

    /**
     * 总行数(通过查询获得)
     */
    private Integer rowCount = 0;

    /**
     * 总页数(通过计算获得)
     */
    private Integer pageCount = 0;

    /**
     * 当前页记录
     */
    private List<T> records;

    public PageObject(Integer pageCurrent, Integer pageSize, Integer rowCount, List<T> records) {
        this.pageCurrent = pageCurrent;
        this.pageSize = pageSize;
        this.rowCount = rowCount;
        this.records = records;

        //计算分页
        this.pageCount = (rowCount - 1) / pageSize + 1;
    }

    public PageObject(Integer pageCurrent, Integer pageSize, Integer rowCount, Integer pageCount, List<T> records) {
        this.pageCurrent = pageCurrent;
        this.pageSize = pageSize;
        this.rowCount = rowCount;
        this.pageCount = pageCount;
        this.records = records;
    }
}
