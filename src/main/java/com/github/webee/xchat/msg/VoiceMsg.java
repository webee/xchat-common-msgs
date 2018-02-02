package com.github.webee.xchat.msg;

import java.util.Map;

/**
 * User: webee
 * Date: 17/10/23
 * Time: 下午5:58
 */
public class VoiceMsg extends FileMsg {
    // 录音长度(单位ms), -1: 无效值
    public int duration;

    public void init(String name, String url, int size, int duration) {
        super.init(name, url, size);
        this.duration = duration;
    }

    public static VoiceMsg of(String name, String url, int size, int duration) {
        VoiceMsg msg = new VoiceMsg();
        msg.init(name, url, size, duration);
        return msg;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = super.toMap();
        map.put("duration", duration);

        return map;
    }

    @Override
    public void digestMap(Map<String, Object> map) {
        super.digestMap(map);
        duration = Utils.safeInt(map.get("duration"), -1);
    }
}
