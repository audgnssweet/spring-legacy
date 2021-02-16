package com.audgnssweet.dao.sqls;

public class MemberRoleDaoSqls {

    public static final String SELECT_ALL_BY_EMAIL =
        "select member_role.id, member_role.member_id, member_role.role_name from member_role "
            + "join member on member_role.member_id = member.id "
            + "where member.email = :email";

    public static final String INSERT =
        "insert into member_role "
            + "(member_id, role_name) values(:memberId, :roleName)";
}
