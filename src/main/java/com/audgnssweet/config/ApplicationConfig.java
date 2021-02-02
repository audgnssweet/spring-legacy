package com.audgnssweet.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

//service, dao layer관련 설정만

@Configuration
@ComponentScan(basePackages = {"com.audgnssweet.dao", "com.audgnssweet.service"})
@Import(DBConfig.class)
public class ApplicationConfig {

}
