package com.yonyou.cloud.demo.tcc.coupon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;

@Configuration
public class FeignConfig {

    @Bean
    public Logger.Level level() {
        // 配置 Feign 的日志级别，输出全部信息
        // 另外需要在 application.yml 中配置 FeignClient 的级别为 DEBUG
        return Logger.Level.FULL;
    }

}