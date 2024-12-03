package org.example.calculator.impl;

import org.example.calculator.Calculator;

public class CalculatorImpl implements Calculator {

    @Override
    public int calc(int number) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return number * 2;
    }

}
