package com.admin.library.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.admin.library.domain.entity.Admin;
import com.admin.library.mapper.AdminMapper;
import com.admin.library.service.AdminService;
import org.springframework.stereotype.Service;

/**
 * (Admin)表服务实现类
 *
 * @author SouthMaple
 * @since 2021-04-07 20:09:56
 */
@Service("adminService")
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
}

