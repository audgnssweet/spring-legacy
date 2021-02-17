package com.audgnssweet.controller;

import com.audgnssweet.annotation.UserAgent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArgumentController {

    @GetMapping("/auth/useragent")
    public String argumentResolverTest(@UserAgent String userAgent) {
        return userAgent;
    }
}
