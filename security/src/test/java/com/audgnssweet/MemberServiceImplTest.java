package com.audgnssweet;

import com.audgnssweet.config.ApplicationConfig;
import com.audgnssweet.config.SecurityConfig;
import com.audgnssweet.entity.Member;
import com.audgnssweet.service.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig
@ContextConfiguration(classes = {SecurityConfig.class, ApplicationConfig.class})
public class MemberServiceImplTest {

    private final MemberService memberService;

    private final PasswordEncoder encoder;

    @Autowired
    public MemberServiceImplTest(MemberService memberService, PasswordEncoder encoder) {
        this.memberService = memberService;
        this.encoder = encoder;
    }

    @Test
    void 넣는거() {
        Member member = new Member();
        member.setName("정");
        member.setPassword(encoder.encode("1234"));
        member.setEmail("audgnssweet@gmail.com");
        final Integer result = memberService.addMember(member);
        Assertions.assertEquals(1, result);
    }
}
