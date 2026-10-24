package com.admiralcoder.mobile.support;

import com.admiralcoder.mobile.config.MobileConfig;
import com.admiralcoder.mobile.driver.DriverFactory;
import com.admiralcoder.mobile.driver.DriverSession;
import io.appium.java_client.android.AndroidDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class MobileTestBase {
    protected AndroidDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void startSession() throws Exception {
        MobileConfig config = MobileConfig.fromEnvironment();
        if (!config.hasAppTarget()) {
            throw new SkipException("QuickTip APK/package is not configured; device tests were not run");
        }
        driver = DriverFactory.create(config);
        DriverSession.attach(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void stopSession() {
        DriverSession.close();
    }
}
