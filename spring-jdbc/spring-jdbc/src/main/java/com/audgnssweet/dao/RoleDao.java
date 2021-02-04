package com.audgnssweet.dao;

import static com.audgnssweet.dao.RoleDaoSqls.*;

import com.audgnssweet.dto.Role;
import java.util.Collections;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final RowMapper<Role> roleRowMapper;

    @Autowired
    public RoleDao(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        roleRowMapper = new BeanPropertyRowMapper<>(Role.class);
    }

    public List<Role> selectAll() {
        return jdbcTemplate.query(SELECT_ALL, Collections.emptyMap(), roleRowMapper);
    }

    public Role selectById(Integer roleId) {
        //직접 sql문 안에 있는 role_id에 roleId를 매핑시켜줬음
        SqlParameterSource parameterSource = new MapSqlParameterSource("role_id", roleId);
        return jdbcTemplate
            .queryForObject(SELECT_BY_ID, parameterSource, roleRowMapper);
    }

    public Integer insert(Role role) {
        //여기서는 sql문 안의 변수가 role의 field명과 같아야 자동으로 매칭시켜줌. 위와 다르다.
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(role);
        return jdbcTemplate.update(INSERT, parameterSource);
    }

    public Integer update(Role role) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(role);
        return jdbcTemplate.update(UPDATE, parameterSource);
    }

    public Integer delete(Integer roleId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("role_id", roleId);
        return jdbcTemplate.update(DELETE, parameterSource);
    }
}
