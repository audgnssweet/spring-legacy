package com.audgnssweet.config;

import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DBConfig {

    @Bean
    public DataSource dataSource() {
        String driverClassName = "com.mysql.jdbc.Driver";
        String Url = "jdbc:mysql://localhost:3306/connectdb?useSSL=false&useUnicode=true&characterEncoding=utf8";
        String userName = "connectuser";
        String password = "connect123!@#";

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(driverClassName);
        basicDataSource.setUrl(Url);
        basicDataSource.setUsername(userName);
        basicDataSource.setPassword(password);

        return basicDataSource;
    }

}
