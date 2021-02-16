package com.audgnssweet.service;

import com.audgnssweet.entity.Member;

public interface MemberService extends UserDbService{

    Integer addMember(Member member);
}
