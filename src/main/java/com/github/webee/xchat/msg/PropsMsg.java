package com.github.webee.xchat.msg;

import com.github.webee.msg.codec.MapMsg;

import java.util.HashMap;
import java.util.Map;

/**
 * User: webee
 * Date: 2017/12/13
 */
public class PropsMsg extends MapMsg {
    private Map<String, Object> props;

    public void setProp(String name, Object value) {
        if (props == null) {
            props = new HashMap<>();
        }
        props.put(name, value);
    }

    public Object getProp(String name) {
        if (props != null) {
            return props.get(name);
        }
        return null;
    }

    public Object getProps() {
        return props;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = super.toMap();
        if (props != null && props.size() > 0) {
            map.put("props", props);
        }

        return map;
    }

    @Override
    public void digestMap(Map<String, Object> map) {
        super.digestMap(map);
        props = (Map<String, Object>) map.get("props");
    }
}
