package com.audgnssweet.dao;

public class RoleDaoSqls {

    public static final String SELECT_ALL =
        "SELECT role_id, description FROM role;";

    public static final String SELECT_BY_ID =
        "SELECT role_id, description FROM role WHERE role_id = :role_id;";

    public static final String INSERT =
        "INSERT INTO role (role_id, description) VALUES(:roleId, :description);";

    public static final String UPDATE =
        "UPDATE role SET description = :description WHERE role_id = :roleId;";

    public static final String DELETE =
        "DELETE FROM role WHERE role_id = :role_id";
}
