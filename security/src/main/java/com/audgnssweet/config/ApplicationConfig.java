package com.audgnssweet.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"com.audgnssweet.service", "com.audgnssweet.dao"})
@Import(DBConfig.class)
public class ApplicationConfig {

}
