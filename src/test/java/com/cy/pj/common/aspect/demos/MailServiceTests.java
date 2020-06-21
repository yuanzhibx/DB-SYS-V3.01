package com.cy.pj.common.aspect.demos;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * AOP 测试类
 * @author Yuanzhibx
 * @Date 2020-06-20
 */
@SpringBootTest
public class MailServiceTests {

    @Test
    void testSendMsg() {
        MailService mailService = new LogMailService(new LogAspect());
        mailService.send("YUANZHIBX");
        //代理对象 -->
    }

}
