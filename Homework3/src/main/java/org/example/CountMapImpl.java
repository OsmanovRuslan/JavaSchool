package org.example;

import java.util.HashMap;
import java.util.Map;

public class CountMapImpl<T> implements CountMap<T> {

    private final Map<T, Integer> map;

    public CountMapImpl() {
        map = new HashMap<>();
    }

    @Override
    public void add(T o) {
        map.put(o, map.getOrDefault(o, 0) + 1);
    }

    @Override
    public int getCount(T o) {
        return map.getOrDefault(o, 0);
    }

    @Override
    public int remove(T o) {
        return map.getOrDefault(o, null) != null ? map.remove(o) : 0;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public void addAll(CountMap<? extends T> source) {
        source.toMap(map);
    }

    @Override
    public Map<T, Integer> toMap() {
        return map;
    }

    @Override
    public void toMap(Map<? super T, Integer> destination) {
        for (Map.Entry<T, Integer> entry : map.entrySet()) {
            destination.put(entry.getKey(), entry.getValue() + destination.getOrDefault(entry.getKey(), 0));
        }
    }
}
