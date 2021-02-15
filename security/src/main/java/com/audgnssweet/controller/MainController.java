package com.audgnssweet.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/main")
    public String nonsecure() {
        return "nonsecure";
    }

    @GetMapping("/securepage")
    public String secure() {
        return "secure";
    }
}
