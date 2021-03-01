package com.audgnssweet.controller;

import com.audgnssweet.entity.Member;
import com.audgnssweet.service.MemberService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    private final MemberService memberService;

    private final PasswordEncoder encoder;

    public MainController(MemberService memberService, PasswordEncoder encoder) {
        this.memberService = memberService;
        this.encoder = encoder;
    }

    @GetMapping("/main")
    @ResponseBody
    public String nonsecure() {
        return "nonsecure";
    }

    @GetMapping("/auth/loginform")
    public String getLoginForm() {
        return "members/loginform";
    }

    @RequestMapping("/auth/loginerror")
    public String getLoginError(@RequestParam(name = "login_error") String loginError) {
        return "members/loginerror";
    }

    @GetMapping("/auth/joinform")
    public String getJoinForm() {
        return "members/joinform";
    }

    @PostMapping("/auth/joinProc")
    public String join(@ModelAttribute Member member) {
        member.setPassword(encoder.encode(member.getPassword()));
        memberService.addMember(member);
        return "redirect:members/loginform";
    }

}
