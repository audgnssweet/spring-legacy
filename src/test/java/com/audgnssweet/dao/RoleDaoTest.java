package com.audgnssweet.dao;

import com.audgnssweet.config.ApplicationConfig;
import com.audgnssweet.dto.Role;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(ApplicationConfig.class)
class RoleDaoTest {

    private RoleDao roleDao;

    @Autowired
    public RoleDaoTest(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Test
    void 다가져오기() {
        //given
        //when
        final List<Role> roles = roleDao.selectAll();
        //then
        roles.forEach(System.out::println);
    }

    @Test
    void 하나만가져오기() {
        //given
        final Integer roleId = 100;
        //when
        Role role = roleDao.selectById(roleId);
        //then
        Assertions.assertEquals(role.getDescription(), "Developer");
    }

    @Test
    void 만들기() {
        //given
        Role role = new Role();
        role.setRoleId(500);
        role.setDescription("CEO");
        //when
        Integer result = roleDao.insert(role);
        //then
        Assertions.assertEquals(1, result);
    }

    @Test
    void 업데이트() {
        //given
        Role role = new Role();
        role.setRoleId(500);
        role.setDescription("CEO");
        //when
        Integer result = roleDao.update(role);
        //then
        Assertions.assertEquals(1, result);
    }

    @Test
    void 삭제() {
        //given
        Integer roleId = 500;
        //when
        Integer result = roleDao.delete(500);
        //then
        Assertions.assertEquals(1,result);
    }
}