package kr.or.connect.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PlusController {

    @GetMapping("/plusform")
    public String getPlusForm() {
        return "plusform";
    }

    @PostMapping("/plus")
    public String postPlus(@RequestParam(name = "value1", required = true) int value1,
        @RequestParam(name = "value2", required = true) int value2, ModelMap map) {
        map.addAttribute("value1", value1);
        map.addAttribute("value2", value2);
        map.addAttribute("result", value1 + value2);

        return "plusresult";
    }
}
