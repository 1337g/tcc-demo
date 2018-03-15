package com.yonyou.cloud.demo.tcc.account.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis 扫描 mapper
 * 
 * @author BENJAMIN
 *
 */
@Configuration
@MapperScan("com.yonyou.cloud.demo.tcc.account.mapper")
public class MybatisMapperConfig {

}
