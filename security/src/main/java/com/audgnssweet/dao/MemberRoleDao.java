package com.audgnssweet.dao;

import static com.audgnssweet.dao.sqls.MemberRoleDaoSqls.SELECT_ALL_BY_EMAIL;

import com.audgnssweet.entity.MemberRole;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRoleDao {

    private final NamedParameterJdbcTemplate template;

    private final RowMapper<MemberRole> mapper;

    public MemberRoleDao(DataSource dataSource) {
        template = new NamedParameterJdbcTemplate(dataSource);
        mapper = BeanPropertyRowMapper.newInstance(MemberRole.class);
    }

    public List<MemberRole> findAllByEmail(String email) {
        final Map<String, String> map = Collections.singletonMap("email", email);
        return template.query(SELECT_ALL_BY_EMAIL, map, mapper);
    }
}
