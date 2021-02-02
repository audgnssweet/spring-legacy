package com.audgnssweet.dao;

public class GuestbookDaoSqls {

    public static final String SELECT_PAGING =
        "SELECT id, name, content, regdate FROM guestbook "
            + "ORDER BY id DESC limit :start, :num;";

    public static final String DELETE_BY_ID =
        "DELETE FROM guestbook WHERE id = :id;";

    public static final String SELECT_COUNT =
        "SELECT COUNT(*) FROM guestbook;";

}
