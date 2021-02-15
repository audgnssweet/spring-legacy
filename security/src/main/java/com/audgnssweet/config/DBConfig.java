package com.audgnssweet.config;

import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@Configuration
@EnableTransactionManagement
public class DBConfig implements TransactionManagementConfigurer {

    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return transactionManager();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(datasource());
    }

    @Bean
    public DataSource datasource() {
        String driverClassName = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/securityexam?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Seoul";
        String userName = "root";
        String password = "root";

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);

        return dataSource;
    }
}
