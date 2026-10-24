package com.admiralcoder.mobile.screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Screen object using verified accessibility IDs supplied from the real APK.
 * No unverified QuickTip selectors are hardcoded in the framework.
 */
public final class QuickTipScreen {
    private final AndroidDriver driver;
    private final WebDriverWait wait;

    public QuickTipScreen(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(12));
    }

    private WebElement visible(String accessibilityId) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.accessibilityId(accessibilityId)));
    }

    public void enterBill(String billInputId, String amount) {
        WebElement input = visible(billInputId);
        input.clear();
        input.sendKeys(amount);
    }

    public void selectTip(String tipButtonId) {
        visible(tipButtonId).click();
    }

    public String totalText(String totalLabelId) {
        return visible(totalLabelId).getText();
    }
}
