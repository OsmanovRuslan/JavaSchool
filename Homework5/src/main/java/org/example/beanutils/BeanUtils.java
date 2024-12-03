package org.example.beanutils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BeanUtils {
    /**
     * Scans object "from" for all getters. If object "to"
     * contains correspondent setter, it will invoke it
     * to set property value for "to" which equals to the property
     * of "from".
     * <p/>
     * The type in setter should be compatible to the value returned
     * by getter (if not, no invocation performed).
     * Compatible means that parameter type in setter should
     * be the same or be superclass of the return type of the getter.
     * <p/>
     * The method takes care only about public methods.
     *
     * @param to   Object which properties will be set.
     * @param from Object which properties will be used to get values.
     */
    public static void assign(Object to, Object from) {
        Method[] methodsOfFrom = from.getClass().getMethods();
        Method[] methodsOfTo = to.getClass().getMethods();

        for (Method fromMethod : methodsOfFrom) {
            for (Method toMethod : methodsOfTo) {
                if (isGetter(fromMethod)){
                    if (isSetter(toMethod)){
                        if (isCompatible(fromMethod, toMethod)) {
                            try {
                                toMethod.invoke(to, fromMethod.invoke(from));
                            } catch (IllegalAccessException | InvocationTargetException e) {
                                System.err.println(e.getMessage());
                            }
                        }
                    }
                }
            }
        }

    }

    private static boolean isCompatible(Method from, Method to) {
        if (from.getReturnType().equals(to.getParameterTypes()[0]) || from.getReturnType().equals(to.getParameterTypes()[0].getSuperclass())) {
            return true;
        }
        return false;
    }

    private static boolean isSetter(Method method) {
        if (!method.getName().startsWith("set")) return false;
        if (method.getParameterTypes().length != 1) return false;
        return true;
    }

    private static boolean isGetter(Method method) {
        if (!method.getName().startsWith("get")) return false;
        if (method.getParameterTypes().length != 0) return false;
        if (void.class.equals(method.getReturnType())) return false;
        return true;
    }

}
