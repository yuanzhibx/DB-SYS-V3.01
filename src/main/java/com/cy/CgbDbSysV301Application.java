package com.cy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 启动类
 * @EnableAsync 启动异步配置(底层会初始化一个线程池)
 *
 * @author yanbingxu
 * @Date 2020-06-10
 */
@EnableAsync
@SpringBootApplication
public class CgbDbSysV301Application {

    public static void main(String[] args) {
        SpringApplication.run(CgbDbSysV301Application.class, args);
    }

}
