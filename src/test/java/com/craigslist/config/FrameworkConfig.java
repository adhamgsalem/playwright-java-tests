package com.craigslist.config;

public class FrameworkConfig {

    public static String baseUrl() {
        return ConfigManager.get("base.url");
    }

    public static boolean headless() {
        return Boolean.parseBoolean(ConfigManager.get("headless"));
    }
}