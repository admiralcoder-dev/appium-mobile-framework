# AdmiralCoder — Appium Mobile Automation Framework

[AdmiralCoder](https://admiralcoder.com) · [GitHub Organization](https://github.com/admiralcoder-dev)

A **Java 21 + Appium 2 + TestNG + Maven** mobile QA automation foundation for the **QuickTip Android application**.

> **Validation boundary:** The default CI workflow runs **configuration and capability unit tests only**. Android UI tests are implemented but **have not been executed against QuickTip** without an APK and emulator/device. Do not interpret a green CI badge as mobile app validation.

## Requirements

- Java 21 and Maven 3.9+
- For actual device testing: Node.js, Appium 2 server, `uiautomator2` driver, Android SDK, emulator or connected device, and a QuickTip APK (or an installed package/activity)

## Framework checks (no emulator required)

```bash
mvn -B verify
```

This executes tests of mobile configuration validation and Appium capability generation. It intentionally excludes the `mobile` group.

## Run Android UI tests (requires a real target)

Install and start Appium:

```bash
npm install -g appium@2
appium driver install uiautomator2
appium
```

Start an Android emulator or connect a device. Set environment variables:

```bash
export APPIUM_SERVER_URL=http://127.0.0.1:4723
export ANDROID_DEVICE_NAME=Android Emulator
export QUICKTIP_APP_PATH=/absolute/path/to/QuickTip.apk
mvn -B test -Dgroups=mobile -DexcludedGroups=
```

On Windows PowerShell:

```powershell
$env:APPIUM_SERVER_URL = "http://127.0.0.1:4723"
$env:ANDROID_DEVICE_NAME = "Android Emulator"
$env:QUICKTIP_APP_PATH = "C:\\path\\to\\QuickTip.apk"
mvn test "-Dgroups=mobile" "-DexcludedGroups="
```

Alternatively, set `QUICKTIP_APP_PACKAGE` and `QUICKTIP_APP_ACTIVITY` for an installed app instead of `QUICKTIP_APP_PATH`. Set `ANDROID_UDID` when multiple devices are connected.

The launch smoke test checks that an Android session starts and exposes a UI hierarchy. It **does not** verify calculations.

### Optional tip calculation scenario

After inspecting the real QuickTip APK's accessibility tree, configure these identifiers:

- `QUICKTIP_BILL_INPUT_ID`
- `QUICKTIP_TIP_15_ID`
- `QUICKTIP_TOTAL_LABEL_ID`

The optional scenario enters 10.00, selects 15%, and checks that the displayed total includes 11.50. Without those verified identifiers, it is explicitly **skipped**, not counted as passed.

## Project structure

```text
src/main/java/com/admiralcoder/mobile/config/MobileConfig.java
src/main/java/com/admiralcoder/mobile/driver/DriverFactory.java
src/main/java/com/admiralcoder/mobile/driver/DriverSession.java
src/main/java/com/admiralcoder/mobile/screens/QuickTipScreen.java
src/test/java/com/admiralcoder/mobile/config/MobileConfigTest.java
src/test/java/com/admiralcoder/mobile/driver/CapabilitiesTest.java
src/test/java/com/admiralcoder/mobile/tests/QuickTipAndroidTest.java
src/test/java/com/admiralcoder/mobile/support/MobileTestBase.java
src/test/java/com/admiralcoder/mobile/support/ScreenshotListener.java
.github/workflows/appium.yml
docs/TEST_STRATEGY.md
```

## Next steps

1. Obtain a distributable QuickTip Android APK.
2. Verify Appium 2 and UiAutomator2 locally or on a CI emulator.
3. Inspect actual UI element accessibility IDs, replace environment placeholders with confirmed locators.
4. Run real launch and calculation tests, add split bill, currency and localization scenarios.
5. Add an Android emulator GitHub Actions job **only after** device tests are verified.

The framework is MIT licensed; see [LICENSE](LICENSE). No app credentials or personal data are committed.
