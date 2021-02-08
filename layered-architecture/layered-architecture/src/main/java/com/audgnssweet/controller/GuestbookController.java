package com.audgnssweet.controller;

import com.audgnssweet.dto.Guestbook;
import com.audgnssweet.service.GuestbookService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GuestbookController {

    private final GuestbookService guestbookService;

    public GuestbookController(GuestbookService guestbookService) {
        this.guestbookService = guestbookService;
    }

    @GetMapping("/list")
    public String getList(
        @RequestParam(name = "start", required = false, defaultValue = "0") Integer start,
        @CookieValue(value = "count", defaultValue = "0") String value,
        ModelMap map,
        HttpServletResponse response) {

        //쿠키 설정
        value = handleCookie(value, response);

        //start로 시작하는 방명록 목록
        final List<Guestbook> list = guestbookService.getGuestbooksOnePage(start);

        //전체 글 수 구하기
        final Integer count = guestbookService.getCount();

        //전체 페이지 수 구하기
        Integer pageCount = getPageCount(count);

        //페이지 스타트 리스트로 만들어주기.
        List<Integer> pageStartList = getPageStartList(pageCount);

        //map에 집어넣기
        map.addAttribute("list", list);
        map.addAttribute("count", count);
        map.addAttribute("pageStartList", pageStartList);
        map.addAttribute("cookieCount", value);

        return "list";
    }

    private String handleCookie(String value, HttpServletResponse response) {
        try {
            int temp = Integer.parseInt(value);
            value = Integer.toString(++temp);
        } catch (Exception e) {
            value = "1";
        }

        Cookie cookie = new Cookie("count", value);
        cookie.setMaxAge(60 * 60 * 24 * 365);
        cookie.setPath("/");

        response.addCookie(cookie);
        return value;
    }

    private List<Integer> getPageStartList(Integer pageCount) {
        List<Integer> pageStartList = new ArrayList<>();
        for (int i = 0; i < pageCount; i++) {
            pageStartList.add(i * GuestbookService.LIMIT);
        }
        return pageStartList;
    }

    private Integer getPageCount(Integer count) {
        int pageCount = count / GuestbookService.LIMIT;
        if (count % GuestbookService.LIMIT != 0) {
            pageCount += 1;
        }
        return pageCount;
    }

    @PostMapping("/write")
    public String postGuestbook(@ModelAttribute Guestbook guestbook,
        HttpServletRequest httpServletRequest) {
        String clientIp = httpServletRequest.getRemoteAddr();
        System.out.println(clientIp);
        guestbookService.addGuestbook(guestbook, clientIp);
        return "redirect:list";
    }

}
