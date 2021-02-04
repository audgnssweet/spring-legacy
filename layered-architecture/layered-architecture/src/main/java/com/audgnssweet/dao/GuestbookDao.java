package com.audgnssweet.dao;

import static com.audgnssweet.dao.GuestbookDaoSqls.*;

import com.audgnssweet.dto.Guestbook;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class GuestbookDao {

    private NamedParameterJdbcTemplate jdbcTemplate;
    private RowMapper<Guestbook> guestbookRowMapper;
    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public GuestbookDao(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        guestbookRowMapper = new BeanPropertyRowMapper<>(Guestbook.class);
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
            .withTableName("guestbook")
            .usingGeneratedKeyColumns("id");
    }

    public List<Guestbook> selectAll(Integer start, Integer num) {
        Map<String, Integer> map = new HashMap<>();
        map.put("start", start);
        map.put("num", num);
        return jdbcTemplate.query(SELECT_PAGING, map, guestbookRowMapper);
    }

    public Integer insert(Guestbook guestbook) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(guestbook);
        return simpleJdbcInsert.executeAndReturnKey(parameterSource).intValue();
    }

    public Integer deleteById(Integer id) {
        final Map<String, Integer> map = Collections.singletonMap("id", id);
        return jdbcTemplate.update(DELETE_BY_ID, map);
    }

    public Integer selectCount() {
        return jdbcTemplate.queryForObject(SELECT_COUNT, Collections.emptyMap(), Integer.class);
    }

}
