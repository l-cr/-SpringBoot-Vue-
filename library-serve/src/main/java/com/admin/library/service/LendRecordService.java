package com.admin.library.service;

import com.admin.library.domain.entity.LendRecord;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * (LendRecord)表服务接口
 *
 * @author SouthMaple
 * @since 2021-04-07 20:09:57
 */
public interface LendRecordService extends IService<LendRecord> {

    /**
     * 分页查询借阅记录
     *
     * @param page 分页
     * @return 查询结果
     */
    Map<String, Object> page(Page<LendRecord> page, String username, String bookName, Long readerId);
}

