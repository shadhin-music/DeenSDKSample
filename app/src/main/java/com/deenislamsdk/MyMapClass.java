package com.deenislamsdk;

import java.util.HashMap;
import java.util.Map;

public class MyMapClass {
    public static Map<String, Boolean> getMap() {
        Map<String, Boolean> map = new HashMap<>();
        map.put("key", true);
        return map;
    }
}
