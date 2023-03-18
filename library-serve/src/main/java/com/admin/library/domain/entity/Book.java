package com.admin.library.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Data;

/**
 * (Book)表实体类
 *
 * @author SouthMaple
 * @since 2021-04-07 20:09:56
 */
@Data
@TableName(value = "book")
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 图书号
     */
    @TableId
    private String isbn;

    /**
     * 书名
     */
    private String bookName;

    /**
     * 作者
     */
    private String author;

    /**
     * 出版社
     */
    private String publisher;

    /**
     * 简介
     */
    private String intro;

    /**
     * 是否借阅（已借阅1，未借阅0）
     */
    private String status;

}

