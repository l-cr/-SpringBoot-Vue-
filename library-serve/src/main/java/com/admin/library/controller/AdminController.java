package com.admin.library.controller;


import com.admin.library.common.base.BaseController;
import com.admin.library.service.AdminService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (Admin)表控制层
 *
 * @author SouthMaple
 * @since 2021-04-07 20:09:56
 */
@RestController
@RequestMapping("/admin")
public class AdminController extends BaseController {
    @Resource
    private AdminService adminService;
}

