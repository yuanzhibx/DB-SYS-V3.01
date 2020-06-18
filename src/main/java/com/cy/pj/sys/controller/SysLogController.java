package com.cy.pj.sys.controller;

import com.cy.pj.common.bo.PageObject;
import com.cy.pj.common.vo.JsonResult;
import com.cy.pj.sys.entity.SysLog;
import com.cy.pj.sys.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 日志模块控制层实现类
 *
 * @author Yuanzhibx
 * @Date 2020-06-10
 */
@Controller
@RequestMapping("/log/")
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;

    @RequestMapping("doFindPageObjects")
    @ResponseBody
    public JsonResult doFindPageObjects(String username, Integer pageCurrent) {
        PageObject<SysLog> pageObjects = sysLogService.findPageObjects(username, pageCurrent);
        return new JsonResult(pageObjects);
        //DispatcherServlet
    }

    @RequestMapping("doDeleteObjects")
    @ResponseBody
    public JsonResult doDeleteObjects(Integer... ids) {
        sysLogService.deleteObjects(ids);
        return new JsonResult("DELETE OK");
    }

}
