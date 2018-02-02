package com.github.webee.xchat.msg;

/**
 * User: webee
 * Date: 2018/2/2
 * Time: 下午8:19
 */
public final class Utils {
    public static int safeInt(Object obj, int def) {
        return obj == null ? def : (int) obj;
    }
}
