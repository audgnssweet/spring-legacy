package kr.or.connect.controller;

import kr.or.connect.dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;

@Controller
public class UserController {

    @GetMapping("/userform")
    public String getUserForm() {
        return "userform";
    }

    @PostMapping("/regist")
    public String postRegist(@ModelAttribute User user) {
        return "regist";
    }
}
