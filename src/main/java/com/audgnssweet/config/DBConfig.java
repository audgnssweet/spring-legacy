package com.audgnssweet.config;

import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

//database와 transaction 관련 설정만

@Configuration
@EnableTransactionManagement    //트랜잭션과 관련된 설정을 자동으로 해준다. 단, 사용자의 transaction 처리를
//위한 platformtransactionmanagement를 처리하기 위해서는 transactionmanagementconfiguerer을 구현
public class DBConfig implements TransactionManagementConfigurer {

    @Bean
    public DataSource dataSource() {
        String driverClassName = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/prj2?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Seoul";
        String userName = "prj2user";
        String password = "";

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);

        return dataSource;
    }

    //transaction을 하기 위함.

    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return transactionManager();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

}
