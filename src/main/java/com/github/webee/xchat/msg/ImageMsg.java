package com.github.webee.xchat.msg;

import java.util.Map;

/**
 * User: webee
 * Date: 17/10/23
 * Time: 下午5:58
 */
public class ImageMsg extends FileMsg {
    // 图片宽度和长度
    public int w;
    public int h;
    // 缩略图，可以是url或者data, 客户端自己判断
    public String thumbnail;

    public void init(String name, String url, int size, int w, int h) {
        super.init(name, url, size);
        this.w = w;
        this.h = h;
    }

    public static ImageMsg of(String name, String url, int size, int w, int h) {
        ImageMsg msg = new ImageMsg();
        msg.init(name, url, size, w, h);
        return msg;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = super.toMap();
        map.put("w", w);
        map.put("h", h);
        map.put("thumbnail", thumbnail);

        return map;
    }

    @Override
    public void digestMap(Map<String, Object> map) {
        super.digestMap(map);
        w = (int) map.get("w");
        h = (int) map.get("h");
        thumbnail = (String) map.get("thumbnail");
    }
}
