package com.admin.library.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Data;

/**
 * (Reader)表实体类
 *
 * @author SouthMaple
 * @since 2021-04-07 20:09:57
 */
@Data
@TableName(value = "reader")
public class Reader implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 读者号
     */
    @TableId(type = IdType.AUTO)
    private Long readerId;

    /**
     * 姓名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 性别（0男，1女，2未知）
     */
    private String gender;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 地址
     */
    private String address;

}

