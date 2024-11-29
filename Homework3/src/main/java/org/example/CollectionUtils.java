package org.example;

import java.util.*;

public class CollectionUtils {

    public static <T> void addAll(List<? extends T> source, List<? super T> destination) {
        destination.addAll(source);
    }

    public static <T> List<T> newArrayList() {
        return new ArrayList<>();
    }

    public static <T> int indexOf(List<? extends T> source, T o) {
        return source.indexOf(o);
    }

    public static <T> List<T> limit(List<? extends T> source, int size) {
        if (size <= 0) {
            return Collections.emptyList();
        } else if (size >= source.size()) {
            return new ArrayList<>(source);
        } else {
            return new ArrayList<>(source.subList(0, size));
        }
    }

    public static <T> void add(List<? super T> source, T o) {
        source.add(o);
    }

    public static <T> void removeAll(List<? super T> removeFrom, List<? extends T> c2) {
        removeFrom.removeAll(c2);
    }

    public static <T> boolean containsAll(List<? extends T> c1, List<? extends T> c2) {
        return new HashSet<>(c1).containsAll(c2);
    }

    public static <T> boolean containsAny(List<? extends T> c1, List<? extends T> c2) {
        for (T e : c1) {
            if (c2.contains(e)){
                return true;
            }
        }
        return false;
    }

    public static <T extends Comparable<? super T>> List<T> range(List<? extends T> list, T min, T max) {
        List<T> result = new ArrayList<>();
        for (T e : list) {
            if (e.compareTo(min) >= 0 && e.compareTo(max) <= 0) {
                result.add(e);
            }
        }
        return result;
    }

    public static <T> List<T> range(List<? extends T> list, T min, T max, Comparator<? super T> comparator) {
        List<T> result = new ArrayList<>();
        for (T e : list) {
            if (comparator.compare(e, min) >= 0 && comparator.compare(e, max) <= 0) {
                result.add(e);
            }
        }
        return result;
    }
}
