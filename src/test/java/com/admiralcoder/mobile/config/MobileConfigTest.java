package com.admiralcoder.mobile.config;

import org.testng.annotations.Test;
import java.util.Map;
import static org.testng.Assert.*;

public class MobileConfigTest {
    @Test
    public void defaultsAreSafeAndDoNotInventAppTarget() {
        MobileConfig config = MobileConfig.from(Map.of());
        assertEquals(config.serverUrl().toString(), "http://127.0.0.1:4723");
        assertEquals(config.deviceName(), "Android Emulator");
        assertFalse(config.hasAppTarget());
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void missingAppTargetFailsExplicitly() {
        MobileConfig.from(Map.of()).requireAppTarget();
    }

    @Test
    public void apkPathIsAValidTarget() {
        MobileConfig config = MobileConfig.from(Map.of("QUICKTIP_APP_PATH", "/tmp/quicktip.apk"));
        assertTrue(config.hasAppTarget());
    }

    @Test
    public void installedAppRequiresPackageAndActivity() {
        assertFalse(MobileConfig.from(Map.of("QUICKTIP_APP_PACKAGE", "com.example.quicktip")).hasAppTarget());
        assertTrue(MobileConfig.from(Map.of(
                "QUICKTIP_APP_PACKAGE", "com.example.quicktip",
                "QUICKTIP_APP_ACTIVITY", ".MainActivity")).hasAppTarget());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void invalidServerProtocolIsRejected() {
        MobileConfig.from(Map.of("APPIUM_SERVER_URL", "file:///tmp/server"));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void malformedServerUrlIsRejected() {
        MobileConfig.from(Map.of("APPIUM_SERVER_URL", "not a url"));
    }

    @Test
    public void configuredUdidIsPreserved() {
        MobileConfig config = MobileConfig.from(Map.of("ANDROID_UDID", "emulator-5554"));
        assertEquals(config.udid(), "emulator-5554");
    }
}
