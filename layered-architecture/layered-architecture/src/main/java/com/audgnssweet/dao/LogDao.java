package com.audgnssweet.dao;

import com.audgnssweet.dto.Log;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

/*
logDao는 입력만 할 것이기에 sql문이 따로 필요 없음. simplejdbcinsert만 있으면 편하게 입력이
가능하기 때문.
 */
@Repository
public class LogDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public LogDao(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
            .withTableName("log")
            .usingGeneratedKeyColumns("id");
    }

    public Integer insert(Log log) {
        //log의 property들을 통해서 sqlparameterSource를 자동으로 만들어준다.
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(log);
        //simplejdbcinsert는 sqlparametersource만 넘겨주면, 즉 table의 변수에 mapping 할 수 있는 녀석만 전해주면
        //자동으로 insert 해주는 편리한 녀석.
        return simpleJdbcInsert.executeAndReturnKey(parameterSource).intValue();
    }
}
