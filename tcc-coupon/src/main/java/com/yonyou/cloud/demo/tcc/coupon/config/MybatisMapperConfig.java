package com.yonyou.cloud.demo.tcc.coupon.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis 扫描 mapper
 * 
 * @author BENJAMIN
 *
 */
@Configuration
@MapperScan("com.yonyou.cloud.demo.tcc.coupon.mapper")
public class MybatisMapperConfig {

}
