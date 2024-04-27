package com.powernode.ssm.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * ClassName: SpringConfig
 * Description:
 * Datetime: 2024/4/1 14:22
 * Author: 老杜@动力节点
 * Version: 1.0
 */
@Configuration
@ComponentScan({"com.powernode.ssm.service"})
@PropertySource("classpath:jdbc.properties")
@Import({DataSourceConfig.class, MyBatisConfig.class})
@EnableTransactionManagement
public class SpringConfig {
}