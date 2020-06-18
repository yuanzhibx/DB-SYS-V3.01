package com.cy.pj.common.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * 业务层对象
 *
 * @author Yuanzhibx
 * @Date 2020-06-16
 */
@Data
public class Node implements Serializable {

    private static final long serialVersionUID = 4828985081977733366L;

    private Integer id;
    private String name;
    private Integer parentId;

}
