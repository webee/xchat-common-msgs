package com.github.webee.xchat.msg.codec;

import com.github.webee.json.JSON;
import com.github.webee.json.JSONObject;
import com.github.webee.msg.codec.MapX;
import com.github.webee.msg.codec.Msg;
import com.github.webee.msg.codec.MsgCodec;

import java.util.HashMap;
import java.util.Map;

/**
 * User: webee
 * Date: 17/10/24
 * Time: 下午1:28
 */
public class JsonMapMsgCodec implements MsgCodec<String> {
    private final JSON json;
    private Map<String, Class<? extends Msg>> typeMsgMappings = new HashMap<>();
    private Map<Class<? extends Msg>, String> msgTypeMappings = new HashMap<>();

    public void addTypeMsgMappings(Map<String, Class<? extends Msg>> typeMsgMappings) {
        for (Map.Entry<String, Class<? extends Msg>> entry : typeMsgMappings.entrySet()) {
            if (!TypeMsgPacker.isTypeValid(entry.getKey())) {
                throw new RuntimeException("invalid msg type");
            }
            try {
                if (! (entry.getValue().newInstance() instanceof MapX)) {
                    throw new RuntimeException("invalid msg class");
                }
            } catch (Throwable t) {
                throw new RuntimeException("bad msg class");
            }
            msgTypeMappings.put(entry.getValue(), entry.getKey());
        }
        this.typeMsgMappings.putAll(typeMsgMappings);
    }

    public JsonMapMsgCodec(JSON json) {
        this.json = json;
    }

    public JsonMapMsgCodec(JSON json, Map<String, Class<? extends Msg>> typeMsgMappings) {
        this(json);
        this.addTypeMsgMappings(typeMsgMappings);
    }

    @Override
    public String encode(Msg msg) {
        if (msg != null && msg instanceof MapX) {
            try {
                String t = msgTypeMappings.get(msg.getClass());
                if (t != null) {
                    return TypeMsgPacker.pack(t, json.serialize(((MapX)msg).toMap()));
                }
            }catch (Throwable t) {
                t.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Msg decode(String msg) {
        if (msg != null) {
            try {
                String t = TypeMsgPacker.unpack(msg);
                if (t != null) {
                    Class<? extends Msg> msgCls = typeMsgMappings.get(t);
                    if (msgCls != null) {
                        JSONObject value = json.parseObject(msg.substring(t.length() + 1));
                        return (Msg) ((MapX)msgCls.newInstance()).digestMap(value.get());
                    }
                }
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
        return null;
    }
}
