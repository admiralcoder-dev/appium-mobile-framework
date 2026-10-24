package com.admiralcoder.mobile.driver;

import com.admiralcoder.mobile.config.MobileConfig;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.Test;
import java.util.Map;
import static org.testng.Assert.*;

public class CapabilitiesTest {
    @Test
    public void apkTargetBuildsAndroidCapabilities() {
        MobileConfig config = MobileConfig.from(Map.of(
                "QUICKTIP_APP_PATH", "/tmp/QuickTip.apk",
                "ANDROID_DEVICE_NAME", "Pixel Emulator"));
        UiAutomator2Options options = DriverFactory.options(config);
        assertEquals(options.getPlatformName().toString(), "ANDROID");
        assertEquals(options.getAutomationName().orElse(""), "UiAutomator2");
        assertEquals(options.getApp().orElse(""), "/tmp/QuickTip.apk");
        assertEquals(options.getDeviceName().orElse(""), "Pixel Emulator");
    }

    @Test
    public void installedAppTargetBuildsPackageAndActivity() {
        MobileConfig config = MobileConfig.from(Map.of(
                "QUICKTIP_APP_PACKAGE", "com.example.quicktip",
                "QUICKTIP_APP_ACTIVITY", ".MainActivity",
                "ANDROID_UDID", "emulator-5554"));
        UiAutomator2Options options = DriverFactory.options(config);
        assertEquals(options.getAppPackage().orElse(""), "com.example.quicktip");
        assertEquals(options.getAppActivity().orElse(""), ".MainActivity");
        assertEquals(options.getUdid().orElse(""), "emulator-5554");
    }
}
