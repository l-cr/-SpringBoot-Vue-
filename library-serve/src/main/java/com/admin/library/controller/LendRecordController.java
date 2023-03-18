package com.admin.library.controller;

import com.admin.library.common.base.BaseController;
import com.admin.library.common.base.R;
import com.admin.library.domain.entity.LendRecord;
import com.admin.library.service.LendRecordService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (LendRecord)表控制层
 *
 * @author SouthMaple
 * @since 2021-04-07 20:09:57
 */
@RestController
@RequestMapping("/lendRecord")
public class LendRecordController extends BaseController {
    @Resource
    private LendRecordService lendRecordService;

    /**
     * 分页查询所有数据
     *
     * @param page     分页对象
     * @param username 读者姓名
     * @param bookName 图书名称
     * @return 所有数据
     */
    @GetMapping("/list")
    public R selectAll(Page<LendRecord> page, String username, String bookName, Long readerId) {
        return result(this.lendRecordService.page(page, username, bookName, readerId));
    }

    /**
     * 添加借阅记录
     *
     * @param lendRecord 借阅信息
     * @return 是否添加成功
     */
    @PostMapping("/add")
    public R addLendRecord(@RequestBody LendRecord lendRecord) {
        return result(lendRecordService.save(lendRecord));
    }

    /**
     * 修改借阅记录
     *
     * @param lendRecord 借阅信息
     * @return 是否修改成功
     */
    @PostMapping("/update")
    public R updateLendRecord(@RequestBody LendRecord lendRecord) {
        UpdateWrapper<LendRecord> wrapper = new UpdateWrapper<>();
        wrapper.lambda().eq(LendRecord::getIsbn, lendRecord.getIsbn()).eq(LendRecord::getReaderId, lendRecord.getReaderId());
        return result(lendRecordService.update(lendRecord, wrapper));
    }

    /**
     * 批量删除借阅记录
     *
     * @return 是否删除成功
     */
    @GetMapping("/delete")
    public R deleteLendRecord(String readerId, String isbn) {
        QueryWrapper<LendRecord> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(LendRecord::getReaderId, readerId).eq(LendRecord::getIsbn, isbn);
        return result(lendRecordService.remove(wrapper));
    }

}
