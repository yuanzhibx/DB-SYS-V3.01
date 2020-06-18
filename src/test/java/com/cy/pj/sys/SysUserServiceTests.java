package com.cy.pj.sys;

import com.cy.pj.common.bo.PageObject;
import com.cy.pj.sys.entity.SysUserDept;
import com.cy.pj.sys.service.SysUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Yuanzhibx
 * @Date 2020-06-18
 */
@SpringBootTest
public class SysUserServiceTests {

    @Autowired
    private SysUserService sysUserService;

    @Test
    void findPageObjectsTest() {
        PageObject<SysUserDept> admin = sysUserService.findPageObjects("admin", 1);
        System.out.println(admin);
    }
}
