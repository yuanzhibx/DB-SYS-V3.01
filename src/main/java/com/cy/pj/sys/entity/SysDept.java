package com.cy.pj.sys.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 部门实体对象
 * @author Yuanzhibx
 * @Date 2020-06-18
 */
@Data
public class SysDept implements Serializable {

    private static final long serialVersionUID = 2547012176567773122L;

    private Integer id;
    private String name;
    private Integer parentId;
    private Integer sort;
    private String note;
    private Date createdTime;
    private Date modifiedTime;
    private String createdUser;
    private String modifiedUser;

}
