package com.audgnssweet.dao;

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
public class GuestbookDaoTest {

    private final GuestbookDao guestbookDao;

    @Autowired
    public GuestbookDaoTest(GuestbookDao guestbookDao) {
        this.guestbookDao = guestbookDao;
    }

    @Test
    void selectGuestbooksPagingTest() {
        final List<Guestbook> guestbooks = guestbookDao.selectAll(0, 5);
        guestbooks.forEach(System.out::println);
    }

    @Test
    @Transactional
    void deleteById() {
        Integer id = 6;
        final Integer result = guestbookDao.deleteById(id);
        Assertions.assertEquals(result, 1);
        System.out.println("처리 건수 : " + result);
    }

    @Test
    void selectCount() {
        final Integer result = guestbookDao.selectCount();
        System.out.println("result : " + result);
    }

    @Test
    void insert() {
        Guestbook guestbook = new Guestbook();
        guestbook.setName("명훈정");
        guestbook.setContent("안녕하세요");
        guestbook.setRegdate(new Date());
        final Integer result = guestbookDao.insert(guestbook);
        Assertions.assertEquals(9, result);
    }

}
