package com.audgnssweet.dao.sqls;

public class MemberDaoSqls {

    public static final String SELECT_BY_EMAIL =
        "SELECT * from member WHERE email = :email";

    public static final String INSERT =
        "insert into member (name, password, email, create_date) "
            + "values(:name, :password, :email, :createDate)";

}
