# Windows + Real Android Phone — Local Setup

**Purpose:** Get the existing Java/TestNG/Appium framework running on a USB-connected Android phone.  
**Status:** Setup instructions; execution has not yet been verified on the user's current machine.

## 1. Prerequisite checks in PowerShell

Run these individually and record the output:

```powershell
java -version
mvn -version
node --version
npm --version
adb devices -l
```

If `adb` is not on PATH, use your existing Android SDK platform-tools path, for example:

```powershell
& "$env:LOCALAPPDATA\Android\Sdk\platform-tools\adb.exe" devices -l
```

Your phone should appear with status `device`, not `unauthorized`. Enable Developer Options and USB debugging on the phone and approve the computer's RSA authorization prompt if needed.

Appium's Android driver requires Java, the Android SDK, `JAVA_HOME`, and `ANDROID_HOME`. See the [official driver requirements](https://appium.github.io/appium-uiautomator2-driver/latest/getting-started/).

## 2. Use the existing framework

```powershell
git clone https://github.com/admiralcoder-dev/appium-mobile-framework.git
cd appium-mobile-framework
mvn -B verify
```

The `mvn verify` command checks the framework only; it **does not run Android UI tests**.

## 3. Appium version compatibility

The repository README currently documents **Appium 2**. Keep that baseline until local compatibility is verified.

```powershell
appium --version
appium driver list --installed
```

If Appium is not installed, the existing baseline can be installed with:

```powershell
npm install -g appium@2
appium driver install uiautomator2@4.2.9
```

**Compatibility pin:** UiAutomator2 4.2.9 is in the Appium 2-compatible major-version range; the latest UiAutomator2 driver is not. **Do not blindly mix major versions.** Newer UiAutomator2 drivers have explicit Appium server compatibility requirements. Check the [driver compatibility matrix](https://appium.github.io/appium-uiautomator2-driver/latest/getting-started/) before upgrading Appium or the driver.

Then validate requirements:

```powershell
appium driver doctor uiautomator2
```

## 4. Identify QuickTip on the device

Use ADB to confirm the installed package:

```powershell
adb shell pm list packages | Select-String quicktip
```

Possible package names:
- Development: `com.admiralcoder.quicktip.dev`
- Production: `com.admiralcoder.quicktip`

Confirm the launch activity rather than assuming one:

```powershell
adb shell cmd package resolve-activity --brief com.admiralcoder.quicktip.dev
```

Use the actual installed package in the command. If the development app is not installed, use a separate test APK or deliberately test the installed production package without altering production code.

## 5. Start the Appium server

In a dedicated PowerShell window:

```powershell
appium
```

Leave this window running while tests execute.

## 6. Configure a second PowerShell window

Example for an installed development app; replace the activity with the one ADB reports:

```powershell
$env:APPIUM_SERVER_URL = "http://127.0.0.1:4723"
$env:ANDROID_DEVICE_NAME = "Android"
$env:QUICKTIP_APP_PACKAGE = "com.admiralcoder.quicktip.dev"
$env:QUICKTIP_APP_ACTIVITY = "com.getcapacitor.BridgeActivity"
```

**Important:** The activity above is only an example, not a verified QuickTip activity. Confirm it before running tests. Alternatively set `QUICKTIP_APP_PATH` to an APK file.

If more than one device is connected, set `ANDROID_UDID` to the exact ADB serial.

## 7. Run the first real-device test

```powershell
mvn test "-Dgroups=mobile" "-DexcludedGroups="
```

The existing test class includes a launch smoke test and a calculation scenario that intentionally **skips** until verified accessibility IDs are supplied. Inspect the app before adding these IDs:

- `QUICKTIP_BILL_INPUT_ID`
- `QUICKTIP_TIP_15_ID`
- `QUICKTIP_TOTAL_LABEL_ID`

Because QuickTip is a Capacitor WebView app, native accessibility IDs may differ from HTML element IDs. A debuggable test build and WebView context may be required for DOM locators.

## 8. Record results

Record the date, phone model, Android version, app version, Appium/driver versions, test count, passes, failures, skips, and screenshot/report locations. Do not upload private signing material or identifiable device logs.

**First guided session:** Start with `adb devices -l` and proceed one command at a time.
