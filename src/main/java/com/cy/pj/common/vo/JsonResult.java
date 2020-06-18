package com.cy.pj.common.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * VO  (View Object / Value Object)
 *      在当前项目中我们借助 vo 封装视图层要呈现的数据
 *
 * @author Yuanzhibx
 * @Date 2020-06-12
 */
@Data
@NoArgsConstructor
public class JsonResult {

    /**
     * 表示消息状态码
     *
     *  1 表示正确数据
     *  0 表示异常数据
     */
    private Integer state;

    /**
     * 状态码对应的具体信息
     */
    private String message;

    /**
     * 数据
     * (基于此属性封装业务层返回的数据)
     */
    private Object data;

    public JsonResult(String message) {
        this.state = 1;
        this.message = message;
    }

    public JsonResult(Object data) {
        this.state = 1;
        this.data = data;
    }

    /**
     * 基于此方法完成对错误信息的初始化
     * @param e
     */
    public JsonResult(Throwable e) {
        // error
        this.state = 0;
        this.message = e.getMessage();
    }

}
