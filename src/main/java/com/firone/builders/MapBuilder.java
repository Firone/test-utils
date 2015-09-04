package com.firone.builders;

import java.util.HashMap;
import java.util.Map;

public class MapBuilder<K, V> {

    private Map<K, V> map = new HashMap<>();

    private MapBuilder() {
    }

    public static <K, V> MapBuilder<K, V> map() {
        return new MapBuilder<>();
    }

    public static <K, V> MapBuilder<K, V> map(K entry, V value) {
        return new MapBuilder<K, V>().entry(entry, value);
    }

    public MapBuilder<K, V> entry(K key, V value) {
        map.put(key, value);
        return this;
    }

    public Map<K, V> build() {
        return map;
    }
}
