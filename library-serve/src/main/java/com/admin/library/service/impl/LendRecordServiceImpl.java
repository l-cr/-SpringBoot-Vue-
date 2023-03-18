package com.admin.library.service.impl;

import com.admin.library.domain.entity.LendRecord;
import com.admin.library.domain.vo.LendRecordVo;
import com.admin.library.mapper.LendRecordMapper;
import com.admin.library.service.LendRecordService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (LendRecord)表服务实现类
 *
 * @author SouthMaple
 * @since 2021-04-07 20:09:57
 */
@Service("lendRecordService")
public class LendRecordServiceImpl extends ServiceImpl<LendRecordMapper, LendRecord> implements LendRecordService {

    /**
     * 分页查询借阅记录
     *
     * @param page 分页
     * @return 查询结果
     */
    @Override
    public Map<String, Object> page(Page<LendRecord> page, String username, String bookName, Long readerId) {
        long skip = (page.getCurrent() - 1) * page.getSize();
        long size = page.getSize();

        List<LendRecordVo> list = this.baseMapper.selectAll(skip, size, username, bookName, readerId);
        Integer total = this.baseMapper.selectCount(null);
        Map<String, Object> map = new HashMap<>();
        map.put("list", list);
        map.put("total", total);
        return map;
    }
}
