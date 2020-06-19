package com.cy.pj.common.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * 借助此对象封装页面上 <input type=checkbox> 对象
 * @author Yuanzhibx
 * @Date 2020-06-19
 */
@Data
public class CheckBox implements Serializable {

    private static final long serialVersionUID = 713508963936780926L;

    private Integer id;
    private String name;

}
