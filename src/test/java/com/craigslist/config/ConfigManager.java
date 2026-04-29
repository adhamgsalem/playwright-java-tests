package com.craigslist.config;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigManager {

    private static Properties props;

    static {
        try {
            props = new Properties();
            props.load(new FileInputStream("src/test/resources/config.properties"));
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config properties");
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}