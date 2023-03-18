package com.admin.library.utils;

import java.util.UUID;

/**
 * UUID生成
 *
 * @author SouthMaple
 * @since 2021-04-10 09:16:37
 */
public class UUIDUtils {

    /**
     * 获取UUID
     *
     * @return uuid
     */
    public static String getUUID() {
        String[] strings = UUID.randomUUID().toString().split("-");

        StringBuilder builder = new StringBuilder();
        for (String str : strings) {
            builder.append(str);
        }
        return builder.toString();
    }
}
