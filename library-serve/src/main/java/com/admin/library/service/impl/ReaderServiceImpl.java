package com.admin.library.service.impl;

import com.admin.library.domain.entity.Reader;
import com.admin.library.mapper.ReaderMapper;
import com.admin.library.service.ReaderService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * (Reader)表服务实现类
 *
 * @author SouthMaple
 * @since 2021-04-07 20:09:57
 */
@Service("readerService")
public class ReaderServiceImpl extends ServiceImpl<ReaderMapper, Reader> implements ReaderService {

    /**
     * 分页查询读者
     *
     * @param page   分页
     * @param reader 查询信息
     * @return 读者列表，读者总计
     */
    @Override
    public Map<String, Object> page(Page<Reader> page, Reader reader) {
        Long readerId = reader.getReaderId();
        String username = reader.getUsername();
        String phone = reader.getPhone();
        String address = reader.getAddress();

        QueryWrapper<Reader> wrapper = new QueryWrapper<>();
        wrapper.lambda().like(!StringUtils.isEmpty(readerId), Reader::getReaderId, readerId)
                .like(!StringUtils.isEmpty(username), Reader::getUsername, username)
                .like(!StringUtils.isEmpty(phone), Reader::getPhone, phone)
                .like(!StringUtils.isEmpty(address), Reader::getAddress, address);

        Page<Reader> selectPage = this.baseMapper.selectPage(page, wrapper);
        Map<String, Object> map = new HashMap<>();
        map.put("list", selectPage.getRecords());
        map.put("total", selectPage.getTotal());
        return map;
    }
}

