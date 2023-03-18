package com.admin.library.service.impl;

import com.admin.library.domain.entity.Book;
import com.admin.library.domain.entity.LendRecord;
import com.admin.library.mapper.BookMapper;
import com.admin.library.service.BookService;
import com.admin.library.service.LendRecordService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * (Book)表服务实现类
 *
 * @author SouthMaple
 * @since 2021-04-07 20:09:57
 */
@Service("bookService")
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements BookService {
    @Resource
    private LendRecordService lendRecordService;

    /**
     * 分页查询图书
     *
     * @param page 分页
     * @param book 查询信息
     * @return 图书列表，图书总计
     */
    @Override
    public Map<String, Object> page(Page<Book> page, Book book) {
        String author = book.getAuthor();
        String bookName = book.getBookName();

        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        wrapper.lambda().like(!StringUtils.isEmpty(author), Book::getAuthor, author)
                .like(!StringUtils.isEmpty(bookName), Book::getBookName, bookName);

        Page<Book> selectPage = this.baseMapper.selectPage(page, wrapper);
        Map<String, Object> map = new HashMap<>();
        map.put("list", selectPage.getRecords());
        map.put("total", selectPage.getTotal());
        return map;
    }

    /**
     * 借阅图书
     * 根据图书的isbn编号借阅图书
     * 如果当前图书未被借阅（status为0），修改status为1，同时新增一条借阅记录
     *
     * @param isbn     图书编号
     * @param readerId 读者编号
     * @return 是否借阅成功
     */
    @Override
    public boolean lendBook(String isbn, Long readerId) {
        Book book = this.baseMapper.selectById(isbn);
        book.setStatus("1");
        int res = this.baseMapper.updateById(book);

        // 新增借阅记录
        LendRecord lendRecord = new LendRecord();
        lendRecord.setIsbn(isbn);
        lendRecord.setReaderId(readerId);
        lendRecord.setLendDate(new Date());
        boolean save = lendRecordService.save(lendRecord);

        return res == 1 && save;
    }

    /**
     * 归还图书
     *
     * @param isbn     图书编号
     * @param readerId 读者编号
     * @return 是否归还成功
     */
    @Override
    public boolean returnBook(String isbn, Long readerId) {
        Book book = this.baseMapper.selectById(isbn);
        book.setStatus("0");
        int res = this.baseMapper.updateById(book);

        // 更新借阅记录状态
        QueryWrapper<LendRecord> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(LendRecord::getStatus, "0").eq(LendRecord::getIsbn, isbn).eq(LendRecord::getReaderId, readerId);
        LendRecord lendRecord = lendRecordService.getOne(wrapper);
        lendRecord.setReturnDate(new Date());
        lendRecord.setStatus("1");
        boolean update = lendRecordService.update(lendRecord, wrapper);

        return res == 1 && update;

    }
}

