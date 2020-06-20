package com.cy.pj.common.aspect.demos;

class LogAspect {
    void before() {
        System.out.println("LogMailService.send start: " + System.currentTimeMillis());
    }

    void after() {
        System.out.println("LogMailService.send end: " + System.currentTimeMillis());
    }
}

/**
 * 子类 (代理对象)
 * CGBLB 代理对象类似此方法
 *
 * @author Yuanzhibx
 * @Date 2020-06-20
 */
class LogMailService extends DefaultMailServiceImpl {
    private LogAspect logAspect;

    public LogMailService(LogAspect logAspect) {
        this.logAspect = logAspect;
    }

    @Override
    public void send(String msg) {
        logAspect.before();
        super.send(msg);
        logAspect.after();
    }
}

/**
 * 核心业务 (目标对象)
 *
 * @author Yuanzhibx
 * @Date 2020-06-20
 */
public class DefaultMailServiceImpl implements MailService {
    @Override
    public void send(String msg) {
        System.out.println("send = " + msg);
    }
}
