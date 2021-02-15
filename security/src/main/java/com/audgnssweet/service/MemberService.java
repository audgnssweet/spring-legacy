package com.audgnssweet.service;

import com.audgnssweet.dao.MemberDao;
import com.audgnssweet.dao.MemberRoleDao;
import com.audgnssweet.entity.Member;
import com.audgnssweet.dto.User;
import com.audgnssweet.dto.UserRole;
import com.audgnssweet.entity.MemberRole;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService implements UserDbService{

    private final MemberDao memberDao;

    private final MemberRoleDao memberRoleDao;

    public MemberService(MemberDao memberDao, MemberRoleDao memberRoleDao) {
        this.memberDao = memberDao;
        this.memberRoleDao = memberRoleDao;
    }

    @Override
    @Transactional(readOnly = true)
    public User getUser(String email) {
        final Member foundUser = memberDao.findByEmail(email);
        return new User(foundUser.getEmail(), foundUser.getPassword());
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserRole> getUserRoles(String username) {
        final List<MemberRole> roles = memberRoleDao.findAllByEmail(username);
        List<UserRole> userRoles = new ArrayList<>();
        roles.forEach(memberRole -> {
            userRoles.add(new UserRole(username, memberRole.getRoleName()));
        });
        return userRoles;
    }

}
