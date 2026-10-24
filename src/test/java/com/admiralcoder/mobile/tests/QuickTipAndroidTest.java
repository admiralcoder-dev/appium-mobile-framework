package com.admiralcoder.mobile.tests;

import com.admiralcoder.mobile.screens.QuickTipScreen;
import com.admiralcoder.mobile.support.MobileTestBase;
import com.admiralcoder.mobile.support.ScreenshotListener;
import org.testng.SkipException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@Listeners(ScreenshotListener.class)
public class QuickTipAndroidTest extends MobileTestBase {
    @Test(groups = "mobile")
    public void quickTipAppLaunchesAndExposesUiHierarchy() {
        String source = driver.getPageSource();
        assertNotNull(source);
        assertFalse(source.isBlank(), "QuickTip must expose a nonempty Android UI hierarchy");
    }

    @Test(groups = "mobile")
    public void preset15PercentTipDisplaysExpectedTotal() {
        String billId = System.getenv("QUICKTIP_BILL_INPUT_ID");
        String tipId = System.getenv("QUICKTIP_TIP_15_ID");
        String totalId = System.getenv("QUICKTIP_TOTAL_LABEL_ID");
        if (billId == null || billId.isBlank()
                || tipId == null || tipId.isBlank()
                || totalId == null || totalId.isBlank()) {
            throw new SkipException("Verified QuickTip accessibility IDs not supplied; calculation test skipped");
        }
        QuickTipScreen screen = new QuickTipScreen(driver);
        screen.enterBill(billId, "10.00");
        screen.selectTip(tipId);
        assertTrue(screen.totalText(totalId).contains("11.50"),
                "A $10.00 bill with a 15% tip should display a total of 11.50");
    }
}
