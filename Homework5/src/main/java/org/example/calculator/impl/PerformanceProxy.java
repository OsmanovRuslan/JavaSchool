package org.example.calculator.impl;

import org.example.calculator.Calculator;
import org.example.calculator.annotation.Metric;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class PerformanceProxy implements InvocationHandler {

    private final Calculator delegate;

    public PerformanceProxy(Calculator delegate) {
        this.delegate = delegate;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (!method.isAnnotationPresent(Metric.class)) return method.invoke(delegate, args);
        long start = System.nanoTime();
        Object invoke = method.invoke(delegate, args);
        System.out.println("Заняло: " + (System.nanoTime() - start) + " наносек.");
        return invoke;
    }

}
