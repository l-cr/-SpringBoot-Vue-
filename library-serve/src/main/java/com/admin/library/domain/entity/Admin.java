package com.admin.library.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Data;

/**
 * (Admin)表实体类
 *
 * @author SouthMaple
 * @since 2021-04-07 20:09:56
 */
@Data
@TableName(value = "admin")
public class Admin implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 管理员编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

}

