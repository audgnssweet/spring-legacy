package com.audgnssweet.controller;

import com.audgnssweet.argumentresolver.HeaderInfo;
import com.audgnssweet.dao.MemberDao;
import com.audgnssweet.entity.Member;
import com.audgnssweet.security.UserDetails;
import com.audgnssweet.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/members")
public class MemberController {

    private final MemberDao memberDao;

    @Autowired
    public MemberController(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "members/welcomepage";
    }

    @GetMapping("/securepage")
    @ResponseBody
    public String secure() {
        return "secure";
    }

    @GetMapping("/memberinfo")
    public String info(@AuthenticationPrincipal UserDetails userDetails, ModelMap map,
        HeaderInfo headerInfo) {

        final Member foundMember = memberDao.findByEmail(userDetails.getUsername());
        map.addAttribute("member", foundMember);
        return "members/memberinfo";
    }
}
