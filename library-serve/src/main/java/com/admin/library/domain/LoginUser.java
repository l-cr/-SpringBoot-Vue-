package com.admin.library.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SouthMaple
 * @since 2021-04-10 10:23:24
 */
public class LoginUser {
    private static int visitCount = 0;
    private static final List<User> userList = new ArrayList<>();
    private static String token;
    private static String role;
    private static Long userId;

    // public static void setToken(String token, String role, Long userId) {
    //     LoginUser.token = token;
    //     LoginUser.role = role;
    //     LoginUser.userId = userId;
    // }

    public static String getToken() {
        return LoginUser.token;
    }

    public static String getRole() {
        return LoginUser.role;
    }

    public static Long getUserId() {
        return LoginUser.userId;
    }

    public static void addVisitCount() {
        LoginUser.visitCount++;
    }

    public static int getVisitCount() {
        return LoginUser.visitCount;
    }

    /**
     * 添加登录用户
     *
     * @param token
     * @param role
     * @param userId
     */
    public static void addUser(String token, String role, Long userId) {
        User user = new User(token, role, userId);
        userList.add(user);
    }

    public static User getUserByToken(String token) {
        for (User user : userList) {
            if (user.getToken().equals(token)) {
                return user;
            }
        }
        return null;
    }
}
