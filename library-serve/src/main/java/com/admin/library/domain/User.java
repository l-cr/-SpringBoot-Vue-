package com.admin.library.domain;

/**
 * @author SouthMaple
 * @since 2021-04-16 20:01:04
 */
public class User {
    private String token;
    private String role;
    private Long userId;

    public User() {
    }

    public User(String token, String role, Long userId) {
        this.token = token;
        this.role = role;
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}