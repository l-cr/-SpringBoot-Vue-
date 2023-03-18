package com.admin.library.service;

import com.admin.library.domain.entity.Book;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * (Book)表服务接口
 *
 * @author SouthMaple
 * @since 2021-04-07 20:09:56
 */
public interface BookService extends IService<Book> {

    /**
     * 分页查询图书
     *
     * @param page 分页
     * @param book 查询信息
     * @return 图书列表，图书总计
     */
    Map<String, Object> page(Page<Book> page, Book book);

    /**
     * 借阅图书
     *
     * @param isbn     图书编号
     * @param readerId 读者编号
     * @return 是否借阅成功
     */
    boolean lendBook(String isbn, Long readerId);

    /**
     * 归还图书
     *
     * @param isbn     图书编号
     * @param readerId 读者编号
     * @return 是否归还成功
     */
    boolean returnBook(String isbn, Long readerId);
}

