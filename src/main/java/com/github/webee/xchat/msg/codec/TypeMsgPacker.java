package com.github.webee.xchat.msg.codec;

/**
 * User: webee
 * Date: 17/10/24
 * Time: 下午1:47
 */
public class TypeMsgPacker {
    /**
     * type should be all lowercase letter.
     * @param t type
     * @return true if type is valid else false
     */
    public static boolean isTypeValid(String t) {
        if (t == null) {
            return false;
        }
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (!Character.isLowerCase(c)) {
                return false;
            }
        }
        return true;
    }

    public static String pack(String t, String msg) {
        return String.format("%s:%s", t, msg);
    }

    public static String unpack(String p) {
        boolean isOk = false;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            if (c == ':') {
                isOk = true;
                break;
            }
            if (Character.isLowerCase(c)) {
                sb.append(c);
                continue;
            }
            return null;
        }
        return isOk ? sb.toString() : null;
    }
}
