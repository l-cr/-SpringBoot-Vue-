package com.admin.library.controller;

import com.admin.library.common.base.BaseController;
import com.admin.library.common.base.R;
import com.admin.library.domain.LoginUser;
import com.admin.library.service.BookService;
import com.admin.library.service.LendRecordService;
import com.admin.library.service.ReaderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author SouthMaple
 * @since 2021-04-16 08:15:29
 */
@RestController
@RequestMapping("/dashboard")
public class DashboardController extends BaseController {
    @Resource
    private BookService bookService;
    @Resource
    private LendRecordService lendRecordService;
    @Resource
    private ReaderService readerService;

    /**
     * 统计数量
     *
     * @return 数量
     */
    @GetMapping("/count")
    public R countLendRecords() {
        int visitCount = LoginUser.getVisitCount();
        int readerCount = readerService.count();
        int lendRecordCount = lendRecordService.count();
        int bookCount = bookService.count();
        Map<String, Object> map = new HashMap<>();
        map.put("visitCount", visitCount);
        map.put("readerCount", readerCount);
        map.put("lendRecordCount", lendRecordCount);
        map.put("bookCount", bookCount);
        return result(map);
    }
}
