package com.audgnssweet.argumentresolver;

import java.util.HashMap;
import java.util.Map;

public class HeaderInfo {

    //map을 직접 사용할 수 없기 때문에.
    private Map<String, String> map;

    public HeaderInfo () {
        map = new HashMap<>();
    }

    public void put (String key, String value) {
        map.put(key, value);
    }

    public String get (String key) {
        return map.get(key);
    }

}
