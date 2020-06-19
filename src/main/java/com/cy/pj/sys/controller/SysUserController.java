package com.cy.pj.sys.controller;

import com.cy.pj.common.vo.JsonResult;
import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 用户管理模块控制层实现类
 *
 * @author Yuanzhibx
 * @Date 2020-06-18
 */
@RestController
@RequestMapping("/user/")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 显示用户管理页面信息
     * @param username
     * @param pageCurrent
     * @return
     */
    @RequestMapping("doFindPageObjects")
    public JsonResult doFindPageObjects(String username,Integer pageCurrent) {
        return new JsonResult(sysUserService.findPageObjects(username, pageCurrent));
    }

    /**
     * 修改用户状态
     * @param id 用户的 id
     * @param valid 状态信息(0, 1)
     * @return
     */
    @RequestMapping("doValidById")
    public JsonResult doValidById(Integer id, Integer valid) {
        sysUserService.validById(id, valid);
        return new JsonResult("UPDATE OK");
    }

    /**
     * 保存 [新增] 的 用户数据 用户角色关系数据
     * @param entity
     * @param roleIds
     * @return
     */
    @RequestMapping("doSaveObject")
    public JsonResult doSaveObject(SysUser entity, Integer[] roleIds) {
        sysUserService.saveObject(entity, roleIds);
        return new JsonResult("SAVE OK");
    }

    /**
     * 基于用户 id 查询用户及相关信息
     * @param id
     * @return
     */
    @RequestMapping("doFindObjectById")
    public JsonResult doFindObjectById(Integer id) {
        Map<String, Object> map = sysUserService.findObjectById(id);
        return new JsonResult(map);
    }

    @RequestMapping("doUpdateObject")
    public JsonResult doUpdateObject(SysUser entity, Integer[] roleIds) {
        sysUserService.updateObject(entity, roleIds);
        return new JsonResult("UPDATE OK");
    }

}
