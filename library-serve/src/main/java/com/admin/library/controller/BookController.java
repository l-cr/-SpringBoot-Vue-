package com.admin.library.controller;


import com.admin.library.common.base.BaseController;
import com.admin.library.common.base.R;
import com.admin.library.domain.entity.Book;
import com.admin.library.service.BookService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Book)表控制层
 *
 * @author SouthMaple
 * @since 2021-04-07 20:09:57
 */
@RestController
@RequestMapping("/book")
public class BookController extends BaseController {
    @Resource
    private BookService bookService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param book 查询实体
     * @return 所有数据
     */
    @GetMapping("/list")
    public R selectAll(Page<Book> page, Book book) {
        return result(this.bookService.page(page, book));
    }

    /**
     * 添加图书
     *
     * @param book 图书
     * @return 是否添加成功
     */
    @PostMapping("/add")
    public R addBook(@RequestBody Book book) {
        return result(bookService.save(book));
    }

    /**
     * 修改图书
     *
     * @param book 图书
     * @return 是否修改成功
     */
    @PostMapping("/update")
    public R updateBook(@RequestBody Book book) {
        return result(bookService.updateById(book));
    }

    /**
     * 根据图书编号批量删除图书
     *
     * @param isbns 图书编号表
     * @return 是否删除成功
     */
    @GetMapping("/delete")
    public R deleteBook(@RequestParam List<String> isbns) {
        return result(bookService.removeByIds(isbns));
    }

    //======================= 读者操作接口 ======================

    /**
     * 借阅图书
     *
     * @param isbn     图书编号
     * @param readerId 读者编号
     * @return 是否借阅成功
     */
    @GetMapping("/lendBook")
    public R lendBook(String isbn, Long readerId) {
        return result(bookService.lendBook(isbn, readerId));
    }

    /**
     * 归还图书
     *
     * @param isbn     图书编号
     * @param readerId 读者编号
     * @return 是否归还成功
     */
    @GetMapping("/returnBook")
    public R returnBook(String isbn, Long readerId) {
        return result(bookService.returnBook(isbn, readerId));
    }
}
