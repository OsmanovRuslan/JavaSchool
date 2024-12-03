package org.example;

import org.example.beanutils.BeanUtils;
import org.example.beanutils.GetterClass;
import org.example.beanutils.SetterClass;
import org.example.calculator.Calculator;
import org.example.calculator.impl.CachedInvocationHandler;
import org.example.calculator.impl.CalculatorImpl;
import org.example.calculator.impl.PerformanceProxy;
import org.example.dto.TestDto;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {

        System.out.println("Task2");
        Class superClass = TestDto.class.getSuperclass();
        Method[] classMethods = TestDto.class.getDeclaredMethods();
        Method[] superClassMethods = superClass.getDeclaredMethods();

        System.out.println("Родительские методы:");
        for (Method method : superClassMethods) {
            System.out.println(method);
        }

        System.out.println("Методы класса:");
        for (Method method : classMethods) {
            System.out.println(method);
        }

        System.out.println("-------");

        System.out.println("Task3");
        Method[] methods = TestDto.class.getMethods();
        for (Method method : methods) {
            if (isGetter(method)) {
                System.out.println("Getter: " + method);
            }
        }

        System.out.println("-------");

        System.out.println("Task4");
        Field[] fields = TestDto.class.getFields();
        for (Field field : fields) {
            try {
                if (isConstantAndNameEqualsValue(field)) {
                    System.out.println("Константы String, у которых значение равно имени: " + field);
                }
            } catch (NullPointerException e) {
                // field не константа.
            } catch (IllegalAccessException e){
                System.err.println("Ошибка с правом доступа.");
            }
        }

        System.out.println("-------");

        System.out.println("Task5");
        Calculator delegate = new CalculatorImpl();
        Calculator calculator = (Calculator) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                delegate.getClass().getInterfaces(),
                new CachedInvocationHandler(delegate));

        run(calculator);

        System.out.println("-------");

        System.out.println("Task6");
        Calculator proxyCalculator = (Calculator) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                delegate.getClass().getInterfaces(),
                new PerformanceProxy(delegate));

        System.out.println(proxyCalculator.calc(3));

        System.out.println("-------");

        System.out.println("Task7");
        GetterClass get = new GetterClass(1, "Hello", true);
        SetterClass set = new SetterClass();

        BeanUtils.assign(set, get);

        System.out.println("field1 of SetterClass: " + set.field1);
        System.out.println("field2 of SetterClass: " + set.field2);

    }

    public static boolean isGetter(Method method){
        return method.getName().startsWith("get") &&
                method.getParameterTypes().length == 0 &&
                !void.class.equals(method.getReturnType());
    }

    public static boolean isConstantAndNameEqualsValue(Field field) throws IllegalAccessException, NullPointerException {
        return field.getName().equals(field.get(null)) &&
                field.getType() == String.class &&
                Modifier.isFinal(field.getModifiers()) &&
                Modifier.isStatic(field.getModifiers());
    }

    private static void run(Calculator calculator) {
        long start = System.currentTimeMillis();
        System.out.println(calculator.calc(1));
        System.out.println(calculator.calc(1));
        System.out.println(calculator.calc(1));
        System.out.println(calculator.calc(1));
        System.out.println(calculator.calc(1));
        System.out.println(calculator.calc(1));
        System.out.println(calculator.calc(1));
        System.out.println(calculator.calc(1));
        System.out.println(calculator.calc(1));
        System.out.println("Заняло : " + (System.currentTimeMillis() - start) + " миллисекунд");
    }
}