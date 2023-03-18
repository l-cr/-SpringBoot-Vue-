package com.admin.library.domain;

import lombok.Data;

/**
 * 登录体
 *
 * @author SouthMaple
 * @since 2021-04-10 09:59:07
 */
@Data
public class LoginBody {
    private String username;
    private String password;
    private String role;
}
