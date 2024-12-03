package org.example.calculator.impl;

import org.example.calculator.Calculator;
import org.example.calculator.annotation.Cache;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CachedInvocationHandler implements InvocationHandler {

    public HashMap<Object, Object> resultByArgs = new HashMap<>();
    private final Calculator delegate;

    public CachedInvocationHandler(Calculator delegate) {
        this.delegate = delegate;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (!method.isAnnotationPresent(Cache.class)) return invoke(method, args);
        if (!resultByArgs.containsKey(key(method, args))) {
            System.out.println("Delegation of " + method.getName());
            Object invoke = invoke(method, args);
            resultByArgs.put(key(method, args), invoke);
        }
        return resultByArgs.get(key(method, args));
    }

    public Object invoke(Method method, Object[] args) throws Throwable{
        try {
            return method.invoke(delegate, args);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Impossible", e);
        } catch (InvocationTargetException e){
            throw e.getCause();
        }
    }

    private Object key(Method method, Object[] args) {
        List<Object> key = new ArrayList<>();
        key.add(method);
        key.addAll(Arrays.asList(args));
        return key;
    }

}
