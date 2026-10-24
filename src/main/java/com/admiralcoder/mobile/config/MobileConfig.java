package com.admiralcoder.mobile.config;

import java.net.URI;
import java.util.Map;

/**
 * Immutable device configuration. Never stores credentials or personal data.
 */
public record MobileConfig(
        URI serverUrl,
        String deviceName,
        String udid,
        String appPath,
        String appPackage,
        String appActivity) {

    public static MobileConfig fromEnvironment() {
        return from(System.getenv());
    }

    public static MobileConfig from(Map<String, String> environment) {
        String url = value(environment, "APPIUM_SERVER_URL", "http://127.0.0.1:4723");
        String device = value(environment, "ANDROID_DEVICE_NAME", "Android Emulator");
        String apk = value(environment, "QUICKTIP_APP_PATH", "");
        String appPackage = value(environment, "QUICKTIP_APP_PACKAGE", "");
        String appActivity = value(environment, "QUICKTIP_APP_ACTIVITY", "");
        String udid = value(environment, "ANDROID_UDID", "");
        URI uri;
        try {
            uri = URI.create(url);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("APPIUM_SERVER_URL must be a valid HTTP(S) URL", exception);
        }
        if (!("http".equals(uri.getScheme()) || "https".equals(uri.getScheme()))
                || uri.getHost() == null) {
            throw new IllegalArgumentException("APPIUM_SERVER_URL must be an HTTP(S) URL with a host");
        }
        if (device.isBlank()) throw new IllegalArgumentException("ANDROID_DEVICE_NAME cannot be blank");
        return new MobileConfig(uri, device, udid, apk, appPackage, appActivity);
    }

    private static String value(Map<String, String> env, String key, String fallback) {
        return env.getOrDefault(key, fallback).trim();
    }

    public boolean hasAppTarget() {
        return !appPath.isBlank() || (!appPackage.isBlank() && !appActivity.isBlank());
    }

    public void requireAppTarget() {
        if (!hasAppTarget()) {
            throw new IllegalStateException(
                    "Set QUICKTIP_APP_PATH or both QUICKTIP_APP_PACKAGE and QUICKTIP_APP_ACTIVITY");
        }
    }
}
