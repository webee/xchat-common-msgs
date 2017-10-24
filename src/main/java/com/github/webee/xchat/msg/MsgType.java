package com.github.webee.xchat.msg;

import com.github.webee.msg.codec.Msg;

import java.util.HashMap;
import java.util.Map;

/**
 * User: webee
 * Date: 17/10/24
 * Time: 下午2:09
 */
public class MsgType {
    public final static Map<String, Class<? extends Msg>> typeMsgs = new HashMap<>();

    static {
        typeMsgs.put("text", TextMsg.class);
        typeMsgs.put("file", FileMsg.class);
        typeMsgs.put("image", ImageMsg.class);
        typeMsgs.put("voice", VoiceMsg.class);
    }
}
