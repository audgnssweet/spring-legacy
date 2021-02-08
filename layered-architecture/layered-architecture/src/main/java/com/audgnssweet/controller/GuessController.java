package com.audgnssweet.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GuessController {

//    @GetMapping("/guess")
//    public String game(
//        @RequestParam(name = "number", required = false) Integer number,
//        HttpSession session, ModelMap map) {
//
//        String message = null;
//
//        if (number == null) {
//            session.setAttribute("count", 0);
//            session.setAttribute("randomNumber", (int) ((Math.random() * 100) + 1));
//            message = "1 - 100 사이의 랜덤숫자 맞추기";
//        } else {
//            Integer count = (Integer) session.getAttribute("count");
//            count++;
//            final Integer randomNumber = (Integer) session.getAttribute("randomNumber");
//
//            //맞춘경우, 작은경우, 큰경우
//            if (randomNumber.equals(number)) {
//                message = Integer.toString(count) + "번 쨰에 맞추셨습니다.";
//                session.removeAttribute("count");
//                session.removeAttribute("randomNumber");
//            } else if (randomNumber > number) {
//                message = "숫자가 작습니다.";
//                session.setAttribute("count", count);
//            } else {
//                message = "숫자가 큽니다";
//                session.setAttribute("count", count);
//            }
//
//            map.addAttribute("message", message);
//
//        }
//
//        return "guess";
//    }

    @GetMapping("/guess")
    public String guess(@RequestParam(required = false) Integer number,
        @CookieValue(required = false, defaultValue = "0") Integer count,
        @CookieValue(required = false) Integer randomNumber,
        HttpServletResponse response,
        ModelMap modelMap) {

        String message = null;

        if(number == null) {
            message = "숫자 맞추기. 1-100중 하나 선택";
            Cookie countCookie = new Cookie("count", Integer.toString(count + 1));
            countCookie.setMaxAge(60 * 60);
            countCookie.setPath("/");
            response.addCookie(countCookie);

            Cookie randomNumberCookie = new Cookie("randomNumber", Integer.toString((int) ((Math.random() * 100) + 1)));
            randomNumberCookie.setMaxAge(60 * 60);
            randomNumberCookie.setPath("/");
            response.addCookie(randomNumberCookie);

            modelMap.addAttribute("count", count);
            modelMap.addAttribute("message", message);
        }else {

            //맞춘경우 작은경우 큰경우
            if (randomNumber.equals(number)) {
                message = Integer.toString(count) + "번만에 맞추셨습니다.";
                Cookie countCookie = new Cookie("count", "0");
                countCookie.setMaxAge(0);
                Cookie randomNumberCookie = new Cookie("randomNumber", "0");
                randomNumberCookie.setMaxAge(0);

                response.addCookie(countCookie);
                response.addCookie(randomNumberCookie);

            } else if (randomNumber > number) {
                message = "작습니다";
                Cookie countCookie = new Cookie("count", Integer.toString(count + 1));
                countCookie.setMaxAge(60 * 60);
                countCookie.setPath("/");
                response.addCookie(countCookie);
            }else {
                message = "큽니다";
                Cookie countCookie = new Cookie("count", Integer.toString(count + 1));
                countCookie.setMaxAge(60 * 60);
                countCookie.setPath("/");
                response.addCookie(countCookie);
            }
            modelMap.addAttribute("message", message);
            modelMap.addAttribute("count", count);

        }

        return "guess";
    }
}
