package org.example.calculator;

import org.example.calculator.annotation.Cache;
import org.example.calculator.annotation.Metric;

public interface Calculator {
    /**
     * Рассчет факториала числа
     * @param number
     */
    @Cache
    @Metric
    int calc(int number);

}
