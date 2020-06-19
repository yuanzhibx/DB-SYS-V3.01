package com.cy.pj.sys.dao;

import com.cy.pj.sys.entity.SysLog;
import com.cy.pj.sys.entity.SysUserDept;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author Yuanzhibx
 * @Date 2020-06-18
 */
@SpringBootTest
public class SysUserDaoTests {

    @Autowired
    private SysUserDao sysUserDao;

//    @Test
//    void getRowCountTest() {
//        int rows = sysUserDao.getRowCount("admin");
//        System.out.println(rows);
//    }

    @Test
    void findPageObjectsTest() {
        List<SysUserDept> admin = sysUserDao.findPageObjects("admin");
        for (SysUserDept sysUserDept : admin) {
            System.out.println(sysUserDept);
        }
    }

}
