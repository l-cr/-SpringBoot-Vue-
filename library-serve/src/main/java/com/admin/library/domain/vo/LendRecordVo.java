package com.admin.library.domain.vo;

import lombok.Data;

import java.util.Date;

/**
 * 借阅记录vo类
 *
 * @author SouthMaple
 * @since 2021-04-13 19:04:18
 */
@Data
public class LendRecordVo {
    private String isbn;
    private String bookName;
    private Long readerId;
    private String username;
    private Date lendDate;
    private Date returnDate;
    private String status;
}
