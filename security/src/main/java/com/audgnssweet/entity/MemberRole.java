package com.audgnssweet.entity;

public class MemberRole {

    private Integer id;
    private Integer memberId;
    private String roleName;

    public MemberRole() {}

    public MemberRole(Integer memberId, String roleName) {
        this.memberId = memberId;
        this.roleName = roleName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getRoleName() {
        return roleName;
    }

    @Override
    public String toString() {
        return "MemberRole{" +
            "id=" + id +
            ", memberId=" + memberId +
            ", roleName='" + roleName + '\'' +
            '}';
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
