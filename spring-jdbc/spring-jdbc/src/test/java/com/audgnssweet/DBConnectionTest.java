package com.audgnssweet;

import static org.junit.jupiter.api.Assertions.fail;

import com.audgnssweet.config.ApplicationConfig;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(ApplicationConfig.class)
public class DBConnectionTest {

    @Autowired
    private DataSource dataSource;

    @Test
    void 디비_연결_됐나요() {
        try {
            final Connection connection = this.dataSource.getConnection();
            if(connection != null){
                System.out.println("connection succeed");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            fail();
        }
    }
}
