package com.admiralcoder.mobile.driver;

import io.appium.java_client.android.AndroidDriver;

public final class DriverSession {
    private static final ThreadLocal<AndroidDriver> CURRENT = new ThreadLocal<>();

    private DriverSession() {}

    public static void attach(AndroidDriver driver) {
        CURRENT.set(driver);
    }

    public static AndroidDriver current() {
        return CURRENT.get();
    }

    public static void close() {
        AndroidDriver driver = CURRENT.get();
        CURRENT.remove();
        if (driver != null) driver.quit();
    }
}
