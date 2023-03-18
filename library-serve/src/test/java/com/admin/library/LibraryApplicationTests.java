package com.admin.library;

import com.admin.library.service.LendRecordService;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.Random;

// @SpringBootTest
class LibraryApplicationTests {
    @Resource
    LendRecordService lendRecordService;

    @Test
    void contextLoads() {
        /*String s = UUID.randomUUID().toString();
        String[] strings = UUID.randomUUID().toString().split("-");

        StringBuilder builder = new StringBuilder();
        for (String str : strings) {
            builder.append(str);
        }

        System.out.println(builder.toString());*/
        // Page page = new Page(0, 1);
        // Map<String, Object> map = lendRecordService.page(page, null, null);
        // System.out.println(page);

        Random random = new Random();

        for (int i = 0; i < 7; i++) {
            System.out.print(random.nextInt(200) + 30 + ", ");
        }
    }

}
