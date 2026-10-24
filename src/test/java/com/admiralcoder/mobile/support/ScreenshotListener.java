package com.admiralcoder.mobile.support;

import com.admiralcoder.mobile.driver.DriverSession;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.OutputType;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.nio.file.Files;
import java.nio.file.Path;

public final class ScreenshotListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        AndroidDriver driver = DriverSession.current();
        if (driver == null) return;
        try {
            Path directory = Path.of("target", "mobile-screenshots");
            Files.createDirectories(directory);
            String filename = (result.getTestClass().getRealClass().getSimpleName() + "-"
                    + result.getMethod().getMethodName()).replaceAll("[^a-zA-Z0-9_-]", "_");
            Files.write(directory.resolve(filename + ".png"),
                    driver.getScreenshotAs(OutputType.BYTES));
        } catch (Exception error) {
            System.err.println("Unable to capture screenshot: " + error.getMessage());
        }
    }
}
