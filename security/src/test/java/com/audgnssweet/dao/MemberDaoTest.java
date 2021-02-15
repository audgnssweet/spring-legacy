package com.audgnssweet.dao;

import com.audgnssweet.config.ApplicationConfig;
import com.audgnssweet.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(ApplicationConfig.class)
public class MemberDaoTest {

    private final MemberDao memberDao;

    @Autowired
    public MemberDaoTest (MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @Test
    void 잘가져와지는지() {
        final Member foundMember = memberDao.findByEmail("carami@example.com");
        System.out.println(foundMember);
    }

}
