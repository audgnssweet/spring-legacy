package com.audgnssweet.dao;

import com.audgnssweet.config.ApplicationConfig;
import com.audgnssweet.entity.MemberRole;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(ApplicationConfig.class)
public class MemberRoleDaoTest {

    private final MemberRoleDao memberRoleDao;

    @Autowired
    public MemberRoleDaoTest(MemberRoleDao memberRoleDao) {
        this.memberRoleDao = memberRoleDao;
    }

    @Test
    void 잘가져와지는지() {
        final List<MemberRole> memberRoles = memberRoleDao.findAllByEmail("carami@example.com");
        memberRoles.forEach(System.out::println);
    }
}
