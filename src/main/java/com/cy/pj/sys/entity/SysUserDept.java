package com.cy.pj.sys.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Yuanzhibx
 * @Date 2020-06-18
 */
@Data
public class SysUserDept implements Serializable {

    private static final long serialVersionUID = 8527659769070554424L;

    private Integer id;
    private String username;
    private String password;
    private String salt;
    private String email;
    private String mobile;
    private Integer valid = 1;
    private SysDept sysDept;
    private Date createdTime;
    private Date modifiedTime;
    private String createdUser;
    private String modifiedUser;

}
