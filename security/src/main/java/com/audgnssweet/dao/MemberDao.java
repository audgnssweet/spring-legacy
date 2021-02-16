package com.audgnssweet.dao;

import static com.audgnssweet.dao.sqls.MemberDaoSqls.INSERT;
import static com.audgnssweet.dao.sqls.MemberDaoSqls.SELECT_BY_EMAIL;

import com.audgnssweet.entity.Member;
import java.util.Collections;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDao {

    private final NamedParameterJdbcTemplate template;

    private final RowMapper<Member> mapper;

    @Autowired
    public MemberDao(DataSource dataSource) {
        template = new NamedParameterJdbcTemplate(dataSource);
        mapper = BeanPropertyRowMapper.newInstance(Member.class);
    }

    public Member findByEmail(String email) {
        final Map<String, String> map = Collections.singletonMap("email", email);
        return template.queryForObject(SELECT_BY_EMAIL, map, mapper);
    }

    public Integer addMember(Member member) {
        BeanPropertySqlParameterSource source = new BeanPropertySqlParameterSource(member);
        return template.update(INSERT, source);
    }

}
