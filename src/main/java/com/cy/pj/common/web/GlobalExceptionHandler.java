package com.cy.pj.common.web;

import com.cy.pj.common.vo.JsonResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ControllerAdvice 描述的类为全局异常处理类
 *      假如 XxxController 类的方法出现异常, 此异常未 try{}catch , 而是继续抛出,
 *      此异常会抛出给 Controller 方法调用者(DispatcherServlet),
 *      此对象会检测抛出异常的 Controller 类中是否有定义异常处理方法, 这个方法能够处理抛出异常,
 *          如果没有, DispatcherServlet 对象还会检测是否有全局的异常处理类,
 *              如果有全局则调用全局异常处理类的相关异常处理方法处理异常
 *
 * @author Yuanzhibx
 * @Date 2020-06-12
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * @ExceptionHandler 注解描述的方法为控制层的异常处理方法, 此注解中传入的异常类型, 即为该方法可以处理的异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public JsonResult doHandleRuntimeException(RuntimeException e) {
        e.printStackTrace();
        return new JsonResult(e);
    }

}
