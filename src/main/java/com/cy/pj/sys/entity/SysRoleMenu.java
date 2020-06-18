package com.cy.pj.sys.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 角色菜单实体对象
 * 通过此对象封装角色以及角色对应的菜单id
 *
 * @author Yuanzhibx
 * @Date 2020-06-17
 */
@Data
public class SysRoleMenu implements Serializable {

    private static final long serialVersionUID = -2729325755621181323L;

    private Integer id;
    private String name;
    private String note;
    /**
     * 角色对应的菜单 id
     */
    private List<Integer> menuIds;

}
