package com.cy.pj.sys.controller;

import com.cy.pj.common.vo.JsonResult;
import com.cy.pj.sys.entity.SysMenu;
import com.cy.pj.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 菜单管理模块控制层
 *
 * @RestController 注解相当于 @Controller + @ResponseBody
 *
 * @author Yuanzhibx
 * @Date 2020-06-15
 */
@RestController
@RequestMapping("/menu/")
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 显示所有菜单记录
     * @return
     */
    @RequestMapping("doFindObjects")
    public JsonResult doFindObjects() {
        return new JsonResult(sysMenuService.findObjects());
    }

    /**
     * 基于菜单 id 删除 菜单记录 以及 相关的菜单关系数据
     * @param id
     * @return
     */
    @RequestMapping("doDeleteObject")
    public JsonResult doDeleteObject(Integer id) {
        sysMenuService.deleteObject(id);
        return new JsonResult("DELETE OK");
    }

    /**
     * 查询所有菜单的 id, name, parentId 信息
     * 显示在 添加菜单/上级菜单, 实现上下级菜单功能
     * @return
     */
    @RequestMapping("doFindZtreeMenuNodes")
    public JsonResult doFindZtreeMenuNodes() {
        return new JsonResult(sysMenuService.findZtreeMenuNodes());
    }

    /**
     * 实现新增菜单保存操作
     * @param entity
     * @return
     */
    @RequestMapping("doSaveObject")
    public JsonResult doSaveObject(SysMenu entity) {
        sysMenuService.saveObject(entity);
        return new JsonResult("SAVE OK");
    }

    /**
     * 实现更新菜单保存操作
     * @param entity
     * @return
     */
    @RequestMapping("doUpdateObject")
    public JsonResult doUpdateObject(SysMenu entity) {
        sysMenuService.updateObject(entity);
        return new JsonResult("UPDATE OK");
    }
}
