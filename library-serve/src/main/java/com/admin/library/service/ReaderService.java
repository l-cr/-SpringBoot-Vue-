package com.admin.library.service;

import com.admin.library.domain.entity.Reader;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * (Reader)表服务接口
 *
 * @author SouthMaple
 * @since 2021-04-07 20:09:57
 */
public interface ReaderService extends IService<Reader> {

    /**
     * 分页查询读者
     *
     * @param page 分页
     * @param reader 查询信息
     * @return 读者列表，读者总计
     */
    Map<String, Object> page(Page<Reader> page, Reader reader);
}

