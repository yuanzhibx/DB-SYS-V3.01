package com.cy.pj.sys.controller;

import com.cy.pj.common.vo.JsonResult;
import com.cy.pj.sys.entity.SysRole;
import com.cy.pj.sys.entity.SysRoleMenu;
import com.cy.pj.sys.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色管理模块控制层实现类
 *
 * @author Yuanzhibx
 * @Date 2020-06-16
 */
@RestController
@RequestMapping("/role/")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 显示角色管理页面信息
     * @param name
     * @param pageCurrent
     * @return
     */
    @RequestMapping("doFindPageObjects")
    public JsonResult doFindPageObjects(String name, Integer pageCurrent) {
        return new JsonResult(sysRoleService.findPageObjects(name, pageCurrent));
    }

    /**
     * 基于菜单 id 删除 菜单记录 以及 相关的菜单关系数据
     * @param id
     * @return
     */
    @RequestMapping("doDeleteObject")
    public JsonResult doDeleteObject(Integer id){
        sysRoleService.deleteObject(id);
        return new JsonResult("delete ok");
    }

    /**
     * 保存角色数据 保存角色和菜单关系数据
     * @param entity
     * @param menuIds
     * @return
     */
    @RequestMapping("doSaveObject")
    public JsonResult doSaveObject(SysRole entity, Integer[] menuIds) {
        sysRoleService.saveObject(entity, menuIds);
        return new JsonResult("SAVE OK");
    }

    /**
     * 基于角色 id 查询角色信息 以及 相关的角色关系数据
     * @param id
     * @return
     */
    @RequestMapping("doFindObjectById")
    public JsonResult doFindObjectById(Integer id) {
        SysRoleMenu roleMenu = sysRoleService.findObjectById(id);
        return new JsonResult(roleMenu);
    }

    @RequestMapping("doUpdateObject")
    public JsonResult doUpdateObject(SysRole entity, Integer[] menuIds) {
        sysRoleService.updateObject(entity, menuIds);
        return new JsonResult("UPDATE OK");
    }

}
