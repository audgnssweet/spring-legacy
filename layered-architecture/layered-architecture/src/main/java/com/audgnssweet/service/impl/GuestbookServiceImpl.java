package com.audgnssweet.service.impl;

import com.audgnssweet.dao.GuestbookDao;
import com.audgnssweet.dao.LogDao;
import com.audgnssweet.dto.Guestbook;
import com.audgnssweet.dto.Log;
import com.audgnssweet.service.GuestbookService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GuestbookServiceImpl implements GuestbookService {

    private final GuestbookDao guestbookDao;

    private final LogDao logDao;

    @Autowired
    public GuestbookServiceImpl(GuestbookDao guestbookDao, LogDao logDao) {
        this.guestbookDao = guestbookDao;
        this.logDao = logDao;
    }

    @Override
    @Transactional(readOnly = true) //내부적으로 read-only 를 사용하게 된다.
    public List<Guestbook> getGuestbooksOnePage(Integer start) {
        return guestbookDao.selectAll(start, GuestbookService.LIMIT);
    }

    @Override
    @Transactional(readOnly = false)
    public Integer deleteGuestbook(Integer id, String ip) {
        final Integer result = guestbookDao.deleteById(id);
        Log log = new Log();
        log.setIp(ip);
        log.setMethod("delete");
        log.setRegdate(new Date());
        log.setIp(ip);
        logDao.insert(log);
        return result;
    }

    /*
    Transactional이 붙어있지 않다면, 아랫단에서 실패해도 실행된 곳까지가 그대로 실행된 채로 남아있다.
    그러므로 transaction 처리는 필수적이다.
     */
    @Override
    @Transactional(readOnly = false)
    public Guestbook addGuestbook(Guestbook guestbook, String ip) {
        guestbook.setRegdate(new Date());
        final Integer resultKey = guestbookDao.insert(guestbook);
        guestbook.setId(resultKey);

        Log log = new Log();
        log.setId(resultKey);
        log.setMethod("insert");
        log.setRegdate(new Date());
        log.setIp(ip);
        logDao.insert(log);

        return guestbook;
    }

    @Override
    @Transactional(readOnly = true)
    public Integer getCount() {
        return guestbookDao.selectCount();
    }

}
