package com.admiralcoder.mobile.driver;

import com.admiralcoder.mobile.config.MobileConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.MalformedURLException;
import java.time.Duration;

public final class DriverFactory {
    private DriverFactory() {}

    public static UiAutomator2Options options(MobileConfig config) {
        config.requireAppTarget();
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(config.deviceName());
        options.setNewCommandTimeout(Duration.ofSeconds(90));
        if (!config.udid().isBlank()) options.setUdid(config.udid());
        if (!config.appPath().isBlank()) {
            options.setApp(config.appPath());
        } else {
            options.setAppPackage(config.appPackage());
            options.setAppActivity(config.appActivity());
        }
        return options;
    }

    public static AndroidDriver create(MobileConfig config) throws MalformedURLException {
        return new AndroidDriver(config.serverUrl().toURL(), options(config));
    }
}
