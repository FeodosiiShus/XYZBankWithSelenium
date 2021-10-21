package com.bank.xyzbank.handlers;

import com.bank.xyzbank.annotations.Key;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Properties;

public class ConfigHandler implements InvocationHandler {

    private final Properties properties;

    public ConfigHandler(Properties properties) {
        this.properties = properties;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        return properties.getProperty(getPropertyName(method));
    }

    private String getPropertyName(Method method) {
        Key annotation = method.getAnnotation(Key.class);
        if (annotation != null) {
            return annotation.value();
        } else {
            return method.getName();
        }
    }
}
