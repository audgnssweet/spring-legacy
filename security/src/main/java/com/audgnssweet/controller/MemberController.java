package com.audgnssweet.controller;

import com.audgnssweet.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/members")
public class MemberController {

    @GetMapping("/loginform")
    public String getLoginForm() {
        return "members/loginform";
    }

    @RequestMapping("/loginerror")
    public String getLoginError(@RequestParam(name = "login_error") String loginError) {
        return "members/loginerror";
    }
}
