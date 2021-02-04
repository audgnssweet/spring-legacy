package com.audgnssweet.service;

import com.audgnssweet.dto.Guestbook;
import java.util.List;

public interface GuestbookService {
    public static final Integer LIMIT = 5;
    public List<Guestbook> getGuestbooksOnePage(Integer start);
    public Integer deleteGuestbook(Integer id, String ip);
    public Guestbook addGuestbook(Guestbook guestbook, String ip);
    public Integer getCount();
}
