package com.audgnssweet.service;

import com.audgnssweet.config.ApplicationConfig;
import com.audgnssweet.dto.Guestbook;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

@SpringJUnitConfig(ApplicationConfig.class)
public class GuestbookServiceTest {

    private GuestbookService guestbookService;

    @Autowired
    public  GuestbookServiceTest (GuestbookService guestbookService) {
        this.guestbookService = guestbookService;
    }

    @Test
    void getOnePage() {
        Integer start = 0;
        final List<Guestbook> onePage = guestbookService.getGuestbooksOnePage(start);
        onePage.forEach(System.out::println);
    }

    @Test
    void delete() {
        Integer id = 9;
        String ip = "127.0.0.1";
        final Integer result = guestbookService.deleteGuestbook(id, ip);
        Assertions.assertEquals(1, result);
    }

    @Test
    void insert() {
        Guestbook guestbook = new Guestbook();
        guestbook.setName("소프트");
        guestbook.setContent("안녕하십니까");
        guestbook.setRegdate(new Date());
        String ip = "127.0.0.2";
        final Guestbook guestbook1 = guestbookService.addGuestbook(guestbook, ip);

        System.out.println(guestbook1);
    }

    @Test
    void count() {
        final Integer count = guestbookService.getCount();
        Assertions.assertEquals(6, count);
    }

}
