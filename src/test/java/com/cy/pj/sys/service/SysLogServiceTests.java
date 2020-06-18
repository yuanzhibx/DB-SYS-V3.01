package com.cy.pj.sys.service;

import com.cy.pj.common.bo.PageObject;
import com.cy.pj.sys.entity.SysLog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 日志模块业务层测试类
 *
 * @author Yuanzhibx
 * @Date 2020-06-12
 */
@SpringBootTest
public class SysLogServiceTests {

    @Autowired
    private SysLogService sysLogService;

    @Test
    void testFindPageObjects() {
        PageObject<SysLog> pageObjects = sysLogService.findPageObjects("admin", 1);
        System.out.println(pageObjects);
    }
}
