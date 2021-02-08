package com.audgnssweet.controller;

import com.audgnssweet.dto.Guestbook;
import com.audgnssweet.service.GuestbookService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/guestbooks")
public class GuestbookApiController {

    private final GuestbookService guestbookService;

    public GuestbookApiController(GuestbookService guestbookService) {
        this.guestbookService = guestbookService;
    }

    @GetMapping
    public Map<String, Object> getGuestbooks(
        @RequestParam(name = "start", required = false, defaultValue = "0") Integer start,
        @CookieValue(value = "count", defaultValue = "0") String value,
        HttpServletResponse response) {

        try {
            int result = Integer.parseInt(value);
            value = Integer.toString(++result);
        } catch (Exception e) {
            value = "1";
        }
        Cookie cookie = new Cookie("count", value);
        cookie.setMaxAge(-1);   //음수로 지정하면 브라우저를 껐다 킬 때마다 초기화
        cookie.setPath("/");
        response.addCookie(cookie);

        List<Guestbook> list = guestbookService.getGuestbooksOnePage(start);

        //전체 글 수 구하기
        final Integer count = guestbookService.getCount();

        //전체 페이지 수 구하기
        Integer pageCount = getPageCount(count);

        //페이지 스타트 리스트로 만들어주기.
        List<Integer> pageStartList = getPageStartList(pageCount);

        //map에 집어넣기
        Map<String, Object> map = new HashMap<>();
        map.put("list", list);
        map.put("count", count);
        map.put("pageStartList", pageStartList);
        map.put("cookieCount", Integer.parseInt(value));

        return map;
    }

    private List<Integer> getPageStartList(Integer pageCount) {
        List<Integer> pageStartList = new ArrayList<>();
        for (int i = 0; i < pageCount; i++) {
            pageStartList.add(i * GuestbookService.LIMIT);
        }
        return pageStartList;
    }

    private Integer getPageCount(Integer count) {
        Integer pageCount = count / GuestbookService.LIMIT;
        if (count % GuestbookService.LIMIT != 0) {
            pageCount += 1;
        }
        return pageCount;
    }

    @PostMapping
    public Guestbook addGuestbook(@RequestBody Guestbook guestbook,
        HttpServletRequest request) {

        String ip = request.getRemoteAddr();
        return guestbookService.addGuestbook(guestbook, ip);
    }

    @DeleteMapping("/{id}")
    public Map<String, String> deleteGuestbook(
        @PathVariable(name = "id", required = true) Integer id,
        HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        final Integer result = guestbookService.deleteGuestbook(id, ip);

        return Collections.singletonMap("delete", ((result == 1) ? "true" : "false"));
    }

}
