package org.example.iterator.impl;

import org.example.iterator.MassiveIterator;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class MassiveIteratorImpl implements MassiveIterator {

    private Object[] massive;

    private int cursor;
    private int lastRet = -1;

    public MassiveIteratorImpl(Object[] massive) {
        this.massive = Arrays.copyOf(massive, massive.length);
    }

    @Override
    public boolean hasNext() {
        return cursor != massive.length;
    }

    @Override
    public Object next() {
        int i = cursor;
        if (i >= massive.length)
            throw new NoSuchElementException();
        cursor = i + 1;
        return massive[lastRet = i];
    }

    @Override
    public void remove() {
        if (lastRet < 0)
            throw new IllegalStateException();
        Objects.checkIndex(lastRet, massive.length);
        final int newSize;
        if ((newSize = massive.length - 1) > lastRet) {
            System.arraycopy(massive, lastRet + 1, massive, lastRet, newSize - lastRet);
        }
        massive = Arrays.copyOf(massive, massive.length - 1);
        for (Object o : massive) {
            System.out.println(o);
        }
        cursor = lastRet;
        lastRet = -1;
    }
}
