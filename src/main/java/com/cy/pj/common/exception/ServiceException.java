package com.cy.pj.common.exception;

/**
 * 自定义业务异常 ServiceException
 *
 * @author Yuanzhibx
 * @Date 2020-06-12
 */
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 5843835376260549700L;

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
