package com.audgnssweet.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DBConfig.class)
@ComponentScan(basePackages = {"com.audgnssweet.dao"})
public class ApplicationConfig {

}
