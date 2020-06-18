package com.cy.pj.sys.dao;

import com.cy.pj.sys.entity.SysLog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author Yuanzhibx
 * @Date 2020-06-10
 */
@SpringBootTest
public class SysLogDaoTests {

    @Autowired
    private SysLogDao sysLogDao;

    @Test
    void getRowCountTest() {
        int rows = sysLogDao.getRowCount("admin");
        System.out.println(rows);
    }

    @Test
    void findPageObjectsTest() {
        List<SysLog> list = sysLogDao.findPageObjects("admin",8,3);
        for (SysLog sysLog : list) {
            System.out.println(sysLog);
        }

    }
}
