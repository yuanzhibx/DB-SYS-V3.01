package com.cy.pj.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 计划: 所有涉及到页面的方法都定义在此 Controller
 * 此 controller 会作为项目中所有页面访问的入口
 *
 * @author Yuanzhibx
 * @Date 2020-06-10
 */
@Controller
@RequestMapping("/")
public class PageController {

    /**
     * 显示主页
     * @return starter.html(主页)
     */
    @RequestMapping("doIndexUI")
    public String doIndexUI() {
        return "starter";
    }

    /**
     * 显示日志列表页面
     * @return log_list.html(日志列表)
     */
//    @RequestMapping("log/log_list")
//    public String doLogUI() {
//        return "sys/log_list";
//    }

    /**
     * 显示分页页面
     * @return page.html(分页页面)
     */
    @RequestMapping("doPageUI")
    public String doPageUI() {
        return "common/page";
    }

    /**
     * 显示菜单管理页面
     * @return menu_list.html(菜单管理)
     */
//    @RequestMapping("menu/menu_list")
//    public String doMenuUI() {
//        System.out.println("PageController.doMenuUI");
//        return "sys/menu_list";
//    }

    /**
     * rest 风格的 url 定义
     * 语法格式: /c/{a}/{b}...;
     *      (其中{}中的内容可以理解为一个变量)
     * @PathVariable 注解用于描述方法参数, 用于获取 url 中与方法参数相同的变量值
     *
     * @param moduleUI
     * @return
     */
    @RequestMapping("{module}/{moduleUI}")
    public String doModuleUI(@PathVariable String moduleUI) {
        System.out.println("PageController.doModuleUI  -- " + moduleUI);
        return "sys/"+moduleUI;
    }

}