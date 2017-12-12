package com.github.webee.xchat.msg;

import java.util.Map;

/**
 * User: webee
 * Date: 17/10/23
 * Time: 下午5:34
 */
public class TextMsg extends PropsMsg {
    public String text;

    public void init(String text) {
        this.text = text;
    }

    public static TextMsg of(String text) {
        TextMsg msg = new TextMsg();
        msg.init(text);
        return msg;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = super.toMap();
        map.put("text", text);

        return map;
    }

    @Override
    public void digestMap(Map<String, Object> map) {
        super.digestMap(map);
        text = (String) map.get("text");
    }
}
