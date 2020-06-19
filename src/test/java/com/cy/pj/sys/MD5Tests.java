package com.cy.pj.sys;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

/**
 * @author Yuanzhibx
 * @Date 2020-06-19
 */
@SpringBootTest
public class MD5Tests {

    @Test
    void testMD501() {
        String s1 = "123456";
        String password = DigestUtils.md5DigestAsHex(s1.getBytes());
        System.out.println(password);
    }
}
