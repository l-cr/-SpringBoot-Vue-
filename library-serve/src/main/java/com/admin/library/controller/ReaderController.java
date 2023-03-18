package com.admin.library.controller;


import com.admin.library.common.base.BaseController;
import com.admin.library.common.base.R;
import com.admin.library.domain.entity.Reader;
import com.admin.library.service.ReaderService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Reader)表控制层
 *
 * @author SouthMaple
 * @since 2021-04-07 20:09:57
 */
@RestController
@RequestMapping("/reader")
public class ReaderController extends BaseController {
    @Resource
    private ReaderService readerService;

    /**
     * 分页查询所有数据
     *
     * @param page   分页对象
     * @param reader 查询实体
     * @return 所有数据
     */
    @GetMapping("/list")
    public R selectAll(Page<Reader> page, Reader reader) {
        return result(this.readerService.page(page, reader));
    }

    /**
     * 添加读者
     *
     * @param reader 读者
     * @return 是否添加成功
     */
    @PostMapping("/add")
    public R addReader(@RequestBody Reader reader) {
        return result(readerService.save(reader));
    }

    /**
     * 修改读者
     *
     * @param reader 读者
     * @return 是否修改成功
     */
    @PostMapping("/update")
    public R updateReader(@RequestBody Reader reader) {
        return result(readerService.updateById(reader));
    }

    /**
     * 根据读者编号批量删除读者
     *
     * @param ids 读者编号表
     * @return 是否删除成功
     */
    @GetMapping("/delete")
    public R deleteReader(@RequestParam List<String> ids) {
        return result(readerService.removeByIds(ids));
    }

    /**
     * 重置密码
     *
     * @param reader 包含读者编号和新密码
     * @return 是否重置成功
     */
    @PostMapping("/resetPwd")
    public R resetPwd(Reader reader) {
        return result(readerService.updateById(reader));
    }

}
