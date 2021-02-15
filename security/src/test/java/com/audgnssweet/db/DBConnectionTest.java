package com.audgnssweet.db;

import com.audgnssweet.config.ApplicationConfig;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(ApplicationConfig.class)
public class DBConnectionTest {

    private final DataSource dataSource;

    @Autowired
    public DBConnectionTest(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Test
    void connectionTest() throws SQLException {
        final Connection connection = dataSource.getConnection();
        if (connection != null) {
            System.out.println("연결 완료");
        } else {
            System.out.println("연결 실패");
        }
    }
}
