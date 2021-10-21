package com.bank.xyzbank.factories;

import com.bank.xyzbank.annotations.Config;
import com.bank.xyzbank.handlers.ConfigHandler;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Proxy;
import java.util.Properties;

public class ConfigFactory<T> {

    private final Class<T> configClass;
    private final Properties configProperties = new Properties();

    public ConfigFactory(Class<T> configClass) {
        this.configClass = configClass;
    }

    public static <S> S createConfig(Class<S> configClass) {
        return new ConfigFactory<>(configClass).create();
    }

    private T create() {
        checkIsInterface();
        readConfigFile(getConfigFilePath());
        return createInstance();
    }

    private void checkIsInterface() {
        if (!configClass.isInterface())
            throw new RuntimeException("Class isn't an interface: " + configClass.getName());
    }

    private String getConfigFilePath() {
        Config annotation = configClass.getAnnotation(Config.class);
        if (annotation == null)
            throw new RuntimeException("Class isn't annotated: " + configClass.getName());
        return annotation.value();
    }

    private void readConfigFile(String configFilePath) {
        try (InputStream fileInputStream = new FileInputStream(configFilePath)) {
            configProperties.load(fileInputStream);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @SuppressWarnings("unchecked")
    public T createInstance() {
        return (T) Proxy.newProxyInstance(configClass.getClassLoader(),
                new Class<?>[]{configClass},
                new ConfigHandler(configProperties));
    }
}
