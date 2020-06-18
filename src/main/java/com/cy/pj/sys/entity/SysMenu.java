package com.cy.pj.sys.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * 菜单实体对象
 *
 * @author Yuanzhibx
 * @Date 2020-06-16
 */
@Data
public class SysMenu implements Serializable {

    private static final long serialVersionUID = 7996522197596293916L;

    private Integer id;
    private String name;
    private String url;
    private Integer type = 1;
    private Integer sort;
    private String note;
    private Integer parentId;
    private String permission;
    private Date createdTime;
    private Date modifiedTime;
    private String createdUser;
    private String modifiedUser;
}
