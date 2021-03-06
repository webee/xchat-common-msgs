package com.github.webee.xchat.msg;

import java.util.Map;

/**
 * User: webee
 * Date: 17/10/23
 * Time: 下午5:35
 */
public class FileMsg extends PropsMsg {
    // 文件名，通过其后缀名判断文件类型
    public String name;
    // 文件url
    public String url;
    // 文件大小(单位bytes), -1: 无效值
    public int size;

    public void init(String name, String url, int size) {
        this.name = name;
        this.url = url;
        this.size = size;
    }

    public static FileMsg of(String name, String url, int size) {
        FileMsg msg = new FileMsg();
        msg.init(name, url, size);
        return msg;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = super.toMap();
        map.put("name", name);
        map.put("url", url);
        map.put("size", size);

        return map;
    }

    @Override
    public void digestMap(Map<String, Object> map) {
        super.digestMap(map);
        name = (String) map.get("name");
        url = (String) map.get("url");
        size = Utils.safeInt(map.get("size"), -1);
    }
}
