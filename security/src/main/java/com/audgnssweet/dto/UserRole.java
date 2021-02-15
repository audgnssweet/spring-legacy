package com.audgnssweet.dto;

public class UserRole {

    private String username;

    private String role;

    public UserRole(String username, String roleName) {
        this.username = username;
        this.role = roleName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
