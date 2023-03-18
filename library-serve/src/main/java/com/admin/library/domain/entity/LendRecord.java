package com.admin.library.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (LendRecord)表实体类
 *
 * @author SouthMaple
 * @since 2021-04-07 20:09:57
 */
@Data
@TableName(value = "lend_record")
public class LendRecord implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 读者号
     */
    private Long readerId;

    /**
     * 图书号
     */
    private String isbn;

    /**
     * 借阅日期
     */
    private Date lendDate;

    /**
     * 归还日期
     */
    private Date returnDate;

    /**
     * 是否归还（未归还0，已归还1）
     */
    private String status;

}

