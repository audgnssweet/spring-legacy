package com.audgnssweet.entity;

import java.time.LocalDateTime;

//entity
public class Member {

    private Integer id;
    private String name;
    private String password;
    private String email;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    public Member(){
        this.createDate = LocalDateTime.now();
    }

    public Member(Integer id, String name, String password, String email) {
        this();
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(LocalDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }


    @Override
    public String toString() {
        return "Member{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", password='" + password + '\'' +
            ", email='" + email + '\'' +
            ", createDate=" + createDate +
            ", modifyDate=" + modifyDate +
            '}';
    }
}
