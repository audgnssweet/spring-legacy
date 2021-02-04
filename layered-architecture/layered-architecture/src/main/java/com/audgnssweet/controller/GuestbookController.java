package com.audgnssweet.controller;

import com.audgnssweet.dto.Guestbook;
import com.audgnssweet.service.GuestbookService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GuestbookController {

    private GuestbookService guestbookService;

    @Autowired
    public GuestbookController(GuestbookService guestbookService) {
        this.guestbookService = guestbookService;
    }

    @GetMapping("/list")
    public String getList(
        @RequestParam(name = "start", required = false, defaultValue = "0") Integer start,
        ModelMap map) {

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

        return "list";
    }

    private List<Integer> getPageStartList(Integer pageCount) {
        List<Integer> pageStartList = new ArrayList<>();
        for(int i = 0; i < pageCount; i++){
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

    @PostMapping("/write")
    public String postGuestbook(@ModelAttribute Guestbook guestbook,
        HttpServletRequest httpServletRequest) {
        String clientIp = httpServletRequest.getRemoteAddr();
        System.out.println(clientIp);
        guestbookService.addGuestbook(guestbook, clientIp);
        return "redirect:list";
    }

}
